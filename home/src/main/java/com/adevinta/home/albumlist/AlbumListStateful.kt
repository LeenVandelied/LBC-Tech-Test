package com.adevinta.home.albumlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.adevinta.core.extensions.rememberFlowWithLifecycle
import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.design.system.components.DsSnackbars
import com.adevinta.design.system.theme.LBCTechTestTheme
import com.adevinta.domain.ResultState
import com.adevinta.home.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AlbumListStateful(
    viewModel: AlbumListViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val sideEffect = rememberFlowWithLifecycle(flow = viewModel.sideEffect)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val albumState = viewModel.albumsState.collectAsState()

    AlbumListScreen(
        albums = albumState.value,
        snackbarHostState = snackbarHostState
    )

    LaunchedEffect(sideEffect) {
        sideEffect.onEach {
            when (it) {
                is NoAlbumsException -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = context.getString(R.string.no_album_error))
                    }
                }

                else -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = context.getString(R.string.generic_error))
                    }
                }
            }
        }.launchIn(this)
    }
}

@Composable
private fun AlbumListScreen(
    albums: ResultState<List<AlbumEntity>>,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        modifier = Modifier
            .background(color = Color.White)
            .padding(WindowInsets.Companion.navigationBars.asPaddingValues()),
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                DsSnackbars.SnackbarError(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    message = it.visuals.message,
                    tagId = ""
                )
            }
        }

    ) { paddings ->
        LazyColumn(
            modifier = Modifier.padding(
                top = paddings.calculateTopPadding(),
                bottom = paddings.calculateBottomPadding(),
                start = paddings.calculateStartPadding(layoutDirection = LayoutDirection.Ltr) + 16.dp,
                end = paddings.calculateEndPadding(layoutDirection = LayoutDirection.Rtl) + 16.dp
            )
        ) {
            if (albums.isFinished) {
                items(items = requireNotNull(albums.getOrNull()), key = { it.id }) { album ->
                    ListItem(
                        headlineContent = { Text(text = album.title) },
                        leadingContent = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = album.title
                            )
                        }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun AlbumListScreenPreview() {
    LBCTechTestTheme {
        AlbumListScreen(
            albums = ResultState.Success(listOf()),
            snackbarHostState = SnackbarHostState()
        )
    }
}