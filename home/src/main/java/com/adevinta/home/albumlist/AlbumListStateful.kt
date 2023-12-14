package com.adevinta.home.albumlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.adevinta.core.extensions.rememberFlowWithLifecycle
import com.adevinta.core.models.AlbumEntity
import com.adevinta.core.models.NoAlbumsException
import com.adevinta.design.system.components.DsButton
import com.adevinta.design.system.components.DsSnackbars
import com.adevinta.design.system.components.DsTexts
import com.adevinta.design.system.theme.LBCTechTestTheme
import com.adevinta.domain.ResultState
import com.adevinta.home.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.PullRefreshState
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

// used to managed actions in the view
private typealias AlbumListActioner = (AlbumListActions) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AlbumListStateful(
    viewModel: AlbumListViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val sideEffect = rememberFlowWithLifecycle(flow = viewModel.sideEffect)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val albumState = viewModel.albumsState.collectAsState()
    val isRefreshing = albumState.value is ResultState.Loading

    // will not display refresh because data are the same
    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = { viewModel.getAlbums(true) }
        )

    AlbumListScreen(
        albums = albumState.value,
        snackbarHostState = snackbarHostState,
        pullRefreshState = pullRefreshState,
        refreshing = isRefreshing
    ) { action ->
        // Here we can manage all actions, we can add for exemple interaction with items in list
        when (action) {
            is OnRefreshAction -> viewModel.getAlbums(true)
        }
    }

    LaunchedEffect(sideEffect) {
        sideEffect
            .onEach {
                when (it) {
                    is NoAlbumsException -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.no_album_error)
                            )
                        }
                    }
                    else -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.generic_error)
                            )
                        }
                    }
                }
            }
            .launchIn(this)
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun AlbumListScreen(
    albums: ResultState<List<AlbumEntity>>,
    snackbarHostState: SnackbarHostState,
    pullRefreshState: PullRefreshState,
    refreshing: Boolean,
    actioner: AlbumListActioner
) {
    Scaffold(
        modifier =
            Modifier.background(color = Color.White)
                .padding(WindowInsets.Companion.navigationBars.asPaddingValues()),
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                DsSnackbars.SnackbarError(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    message = it.visuals.message
                )
            }
        },
        topBar = {
            TopAppBar(
                title = { DsTexts.TitleMedium(title = stringResource(id = R.string.my_albums)) },
                colors =
                    TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
            )
        }
    ) { paddings ->
        Box(modifier = Modifier.fillMaxWidth()) {
            when (albums) {
                is ResultState.Failure -> {
                    Column(
                        modifier =
                            Modifier.padding(
                                    top = paddings.calculateTopPadding(),
                                    start =
                                        paddings.calculateStartPadding(
                                            layoutDirection = LayoutDirection.Ltr
                                        ),
                                    end =
                                        paddings.calculateEndPadding(
                                            layoutDirection = LayoutDirection.Rtl
                                        )
                                )
                                .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.size(8.dp))
                        DsTexts.TitleLarge(title = stringResource(id = R.string.an_error_occured))
                        Spacer(modifier = Modifier.size(8.dp))
                        DsTexts.BodyMedium(
                            title = stringResource(id = R.string.an_error_occured_info)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        DsButton.ErrorButton(text = stringResource(id = R.string.try_again)) {
                            actioner(OnRefreshAction)
                        }
                    }
                }
                is ResultState.Uninitialized,
                is ResultState.Loading -> {
                    Column(
                        modifier =
                            Modifier.padding(
                                    top = paddings.calculateTopPadding(),
                                    start =
                                        paddings.calculateStartPadding(
                                            layoutDirection = LayoutDirection.Ltr
                                        ),
                                    end =
                                        paddings.calculateEndPadding(
                                            layoutDirection = LayoutDirection.Rtl
                                        )
                                )
                                .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.size(8.dp))
                        DsTexts.TitleLarge(title = stringResource(id = R.string.data_are_loading))
                        Spacer(modifier = Modifier.size(8.dp))
                        DsTexts.BodyMedium(
                            title = stringResource(id = R.string.data_are_loading_info)
                        )
                    }
                }
                is ResultState.Success -> {
                    LazyColumn(
                        modifier =
                            Modifier.padding(
                                    top = paddings.calculateTopPadding(),
                                    start =
                                        paddings.calculateStartPadding(
                                            layoutDirection = LayoutDirection.Ltr
                                        ),
                                    end =
                                        paddings.calculateEndPadding(
                                            layoutDirection = LayoutDirection.Rtl
                                        )
                                )
                                .pullRefresh(state = pullRefreshState)
                    ) {
                        items(items = albums.data, key = { it.id }) { album ->
                            // Add header User-Agent
                            val url =
                                GlideUrl(
                                    album.thumbnailUrl,
                                    LazyHeaders.Builder()
                                        .addHeader(
                                            "User-Agent",
                                            System.getProperty("http.agent") ?: "null"
                                        )
                                        .build()
                                )
                            ListItem(
                                headlineContent = {
                                    DsTexts.BodyMedium(title = album.title, maxLines = 2)
                                },
                                leadingContent = {
                                    Box(modifier = Modifier.align(Alignment.Center)) {
                                        GlideImage(
                                            model = url,
                                            contentDescription = album.title,
                                            loading = placeholder(R.drawable.ic_image_load),
                                            failure = placeholder(R.drawable.ic_error),
                                            modifier = Modifier.size(56.dp, 56.dp)
                                        )
                                    }
                                }
                            )
                            Divider()
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = refreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AlbumListScreenPreview() {
    LBCTechTestTheme {
        AlbumListScreen(
            albums = ResultState.Success(listOf()),
            snackbarHostState = SnackbarHostState(),
            pullRefreshState = rememberPullRefreshState(refreshing = true, onRefresh = {}),
            refreshing = false
        ) {}
    }
}
