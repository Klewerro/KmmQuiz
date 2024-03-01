import SwiftUI
import shared

struct ContentView: View {
    let appModule: AppModule
	var body: some View {
        GetQuestionListScreen(getQuestionListUseCase: appModule.getQuestionListUseCase)
	}
}
