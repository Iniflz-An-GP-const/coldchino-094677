package nazema.coldchino.info.ui.composable.app_root.background.motion

import androidx.compose.runtime.Composable
import nazema.coldchino.info.ui.composable.app_root.background.BackgroundMotion

private val Static = BackgroundMotion(shift = { 0f }, alpha = { 1f })

@Composable
fun rememberBackgroundMotion(): BackgroundMotion = Static
