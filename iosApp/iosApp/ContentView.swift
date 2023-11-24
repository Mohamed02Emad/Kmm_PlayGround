import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject private var viewModel = ViewModel()
      
      var body: some View {
          AppHeader(text: "SparceX Rockets")
          RocketsList(rockets: viewModel.rocketsList) .onAppear {
              Task {
                         await self.viewModel.initData()
                     }
          }

      }
    
    struct AppHeader: View {
        var text: String
        var body: some View {
            HStack {
                Spacer()
                Text(text)
                    .font(.system(size: 18, weight: .bold ))
                Spacer()
            }
            .padding(.horizontal, 12)
            .padding(.top, 20)
            .padding(.bottom, 8)
        }
    }
    
    struct RocketsList: View {
        var rockets: [RocketLaunch]
        var body: some View {
            ScrollView {
                LazyVStack(spacing: 8) {
                    
                    ForEach(rockets, id: \.self) { rocket in
                        VStack(alignment: .leading, spacing: 8) {
                            HStack {
                                Text("Mission Name : ")
                                    .font(.system(size: 12))
                                Spacer()
                                Text(rocket.missionName)
                                    .font(.system(size: 12))
                                    .foregroundColor(.black)
                            }
                            HStack {
                                Text("Mission Date  : ")
                                    .font(.system(size: 12))
                                Spacer()
                                Text(rocket.launchDate)
                                    .font(.system(size: 12))
                                    .foregroundColor(.black)
                            }
                        }
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(Color.gray)
                        .cornerRadius(14)
                        .onTapGesture {

                        }
                    }
                    
//                    ForEach(rockets, id: \.self) { rocket in
//                                   Text(rocket)
//                                       // Additional styling and layout for each item
//                               }
                    
                }
                .padding(.all, 20)
            }
        }
    }
}

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        
        @Published var isLoading = false
        @Published var rocketsList: [RocketLaunch] = []

 
        func initData() async {
            rocketsList = await try! RocketsRepository().getLastSuccessfulLaunchDateAsList()
        }


        
        
//        init() {
//
//            Task {
//                do {
//                    var rocketsFlow = RocketsRepository().getLastSuccessfulLaunchDate()
//
//                    for await state in rocketsFlow  {
//                        switch state {
//                        case .error:
//                            isLoading = false
//
//                        case .loading:
//                            isLoading = true
//
//                        case .success(let data):
//                            if let data = data {
//                                rocketsList += data
//                            }
//                            isLoading = false
//                        }
//                    }
//
//                } catch {
//                    isLoading = false
//                    // Handle error
//                }
//            }
//        }

        
        
    }
}
