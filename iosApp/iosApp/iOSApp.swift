import SwiftUI
import shared

@main
struct iOSApp: App {
    let appModule = AppModule()
    
	var body: some Scene {
		WindowGroup {
            ContentView(appModule: appModule)
		}
	}
}
