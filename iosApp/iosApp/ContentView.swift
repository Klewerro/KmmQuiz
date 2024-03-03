import SwiftUI
import shared

struct ContentView: View {
    let appModule: AppModule
	var body: some View {
        TabView {
            GetQuestionListScreen(
                getQuestionListUseCase: appModule.getQuestionListUseCase,
                keyValueStorage: appModule.keyValueStorage,
                localDbDataSource: appModule.localDbDataSource
            )
            .tabItem {
                Image(systemName: "square.and.arrow.down")
                Text("Get questions")
            }
            
            SavedQuestionsScreen(
                savedQuestionsUseCase: appModule.savedQuestionsUseCase,
                localDbDataSource: appModule.localDbDataSource
            )
            .tabItem {
                Image(systemName: "scroll")
                Text("Saved questions")
            }
        }
        
        
	}
}
