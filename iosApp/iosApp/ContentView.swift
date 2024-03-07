import SwiftUI
import shared

struct ContentView: View {
    let appModule: AppModule
    
    @ObservedObject var getQuestionListViewModel: IOSGetQuestionListViewModel
    @ObservedObject var savedQuestionsViewModel: IOSSavedQuestionsViewModel
    @ObservedObject var quizViewModel: IOSQuizViewModel
    
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
        self.quizViewModel = IOSQuizViewModel(quizListUseCase: appModule.quizListUseCase)
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
            
            NavigationView {
                QuizScreen(viewModel: quizViewModel)
            }
            .tabItem {
                Image(systemName: "doc.questionmark")
                Text("Quiz")
            }
            .badge(quizViewModel.state.quizList.count)
        }
        .onAppear {
            getQuestionListViewModel.startObserving()
            savedQuestionsViewModel.startObserving()
            quizViewModel.startObserving()
        }
        .onDisappear {
            getQuestionListViewModel.dispose()
            savedQuestionsViewModel.dispose()
            quizViewModel.dispose()
        }
	}
}
