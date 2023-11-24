import SwiftUI
import shared

struct ContentView: View {
    
    var body: some View {
        ScrollView {
            LazyVStack(alignment: .leading, spacing: 0, pinnedViews: [.sectionHeaders]) {
               

                Section{
                    ForEach(0..<50, id: \.self) { index in
                        IndexCard(index: index)
                            .padding(.horizontal, 12)
                            .padding(.vertical, 12)
                    }
                }header: {
                    var days = Greeting().greet()
                    MyHeader(text: days + " One")
                        .padding(.horizontal, 12)

                }
                Section{
                    ForEach(0..<50, id: \.self) { index in
                        IndexCard(index: index)
                            .padding(.horizontal, 12)
                            .padding(.vertical, 12)
                    }
                }header: {
                    var days = Greeting().greet()
                    MyHeader(text: days + " Two")
                        .padding(.horizontal, 12 )

                }

            }
            
        }
    }
    
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ContentView()
        }
    }
    struct MyHeader: View {
        var text: String
        
        var body: some View {
            Text(text)
                .frame(maxWidth: .infinity)
                .background(Color.red)
                .cornerRadius(50)
        }
    }
    
    struct IndexCard: View {
        var index: Int
        
        var body: some View {
            Text("Index \(index)")
                .frame(maxWidth: .infinity, minHeight: 40)
                .background(Color.blue)
                .cornerRadius(50)
        }
    }
}
