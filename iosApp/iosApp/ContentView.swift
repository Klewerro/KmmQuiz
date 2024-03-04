import SwiftUI
import shared

struct ContentView: View {
    let appModule: AppModule
    
    @ObservedObject var getQuestionListViewModel: IOSGetQuestionListViewModel
    @ObservedObject var savedQuestionsViewModel: IOSSavedQuestionsViewModel
    
    init(appModule: AppModule) {
        self.appModule = appModule
        self.getQuestionListViewModel = IOSGetQuestionListViewModel(
            getQuestionListUseCase: appModule.getQuestionListUseCase,
            keyValueStorage: appModule.keyValueStorage,
            localDbDataSource: appModule.localDbDataSource
        )
        self.savedQuestionsViewModel = IOSSavedQuestionsViewModel(
            savedQuestionsUseCase: appModule.savedQuestionsUseCase,
            localDbDataSource: appModule.localDbDataSource
        )
    }
    
	var body: some View {
        TabView {
            GetQuestionListScreen(viewModel: getQuestionListViewModel)
            .tabItem {
                Image(systemName: "square.and.arrow.down")
                Text("Get questions")
            }
            .badge(getQuestionListViewModel.state.questions.count)
            
            SavedQuestionsScreen(viewModel: savedQuestionsViewModel)
            .tabItem {
                Image(systemName: "scroll")
                Text("Saved questions")
            }
            .badge(savedQuestionsViewModel.state.savedQuestions.count)
        }
        .onAppear {
            getQuestionListViewModel.startObserving()
            savedQuestionsViewModel.startObserving()
        }
        .onDisappear {
            getQuestionListViewModel.dispose()
            savedQuestionsViewModel.dispose()
        }
	}
}
