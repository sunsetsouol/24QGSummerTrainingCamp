import "./App.css";
import Router from "./router";
import { AdminProvider } from "./utils/context"; // 导入 AdminProvider
function App() {
  return (
    <AdminProvider>
      <div className="App">
        <Router />
      </div>
    </AdminProvider>
  );
}

export default App;
