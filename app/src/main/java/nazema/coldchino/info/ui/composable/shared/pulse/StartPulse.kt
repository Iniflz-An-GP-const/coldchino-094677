package nazema.coldchino.info.ui.composable.shared.pulse

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import nazema.coldchino.info.ui.composable.shared.pulse.timing.StartPulseTiming
import nazema.coldchino.info.ui.composable.shared.pulse.pulseEffect

@Composable
fun Modifier.startPulse(cornerRadius: Dp): Modifier = pulseEffect(cornerRadius, StartPulseTiming)
