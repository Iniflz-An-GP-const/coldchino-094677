package nazema.coldchino.info.ui.composable.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import nazema.coldchino.info.data.model.PuzzleLevel
import nazema.coldchino.info.ui.composable.screen.about.NMDNOAboutScreen
import nazema.coldchino.info.ui.composable.screen.game.NMDNOGameScreen
import nazema.coldchino.info.ui.composable.screen.game_records.NMDNOGameRecordsScreen
import nazema.coldchino.info.ui.composable.screen.main_menu.NMDNOMainMenuScreen
import nazema.coldchino.info.ui.composable.screen.puzzle_setup.NMDNOPuzzleSetupScreen
import nazema.coldchino.info.ui.composable.screen.splash.NMDNOSplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val activity = LocalActivity.current

    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            NMDNOSplashScreen(
                onMainMenuScreenNavigation = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.MainMenu> {
            NMDNOMainMenuScreen(
                onPuzzleSelectionScreenNavigation = { navController.navigate(route = NavRoute.PuzzleSetup) },
                onGameRecordsScreenNavigation = { navController.navigate(route = NavRoute.GameRecords) },
                onAboutScreenNavigation = { navController.navigate(route = NavRoute.About) },
                onExit = { activity?.finishAffinity() }
            )
        }

        composable<NavRoute.PuzzleSetup> {
            NMDNOPuzzleSetupScreen(
                onStartGame = { puzzleId, level ->
                    navController.navigate(
                        route = NavRoute.Game(
                            puzzleId = puzzleId,
                            level = level.name,
                        )
                    )
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<NavRoute.Game> { backStackEntry ->
            val gameRoute: NavRoute.Game = backStackEntry.toRoute()
            NMDNOGameScreen(
                puzzleId = gameRoute.puzzleId,
                level = PuzzleLevel.valueOf(gameRoute.level),
                onExitToMainMenu = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.About> {
            NMDNOAboutScreen(onBackClick = { navController.popBackStack() })
        }

        composable<NavRoute.GameRecords> {
            NMDNOGameRecordsScreen(onBackClick = { navController.popBackStack() })
        }
    }
}