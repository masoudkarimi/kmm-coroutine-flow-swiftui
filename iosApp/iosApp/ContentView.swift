import SwiftUI
import Shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        VStack {
            Button("Start Receiving") {
                Task {
                    await viewModel.startObserving()
                }
            }

            ListView(numbers: viewModel.values)
        }
    }
}

struct ListView: View {
    let numbers: Array<String>
    
    var body: some View {
        List(numbers, id: \.self) {
            Text($0)
        }
    }
}

extension ContentView {
    
    @MainActor
    class ViewModel: ObservableObject {
        @Published var values: [String] = []
        
        
        func startObserving() async {
            let emittedValues = Greeting().countToTen()
            for await number in emittedValues {
                values.append(number)
            }
        }
    }
}



struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
    }
}
