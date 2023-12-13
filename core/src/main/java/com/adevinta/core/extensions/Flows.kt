package com.adevinta.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow

/*
   Listen to a [flow] linked to a [lifecycle] according to the [minActivateState].
   If the state is below [minActivateState], the [flow] won't be listened.
   More info on : https://medium.com/androiddevelopers/repeatonlifecycle-api-design-story-8670d1a7d333
*/
@Composable
fun <T> rememberFlowWithLifecycle(
    flow: Flow<T>,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    minActivateState: Lifecycle.State = Lifecycle.State.STARTED
): Flow<T> =
    remember(flow, lifecycle) {
        flow.flowWithLifecycle(lifecycle = lifecycle, minActiveState = minActivateState)
    }
