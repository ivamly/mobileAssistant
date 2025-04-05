import { Route, Routes } from "react-router-dom";

import IndexPage from "@/pages/index";
import Workspaces from "./pages/workspaces";
import Workspace from "./pages/workspace";
import Create from "./pages/create";
function App() {
  return (
    <Routes>
      <Route element={<IndexPage />} path="/" />
      <Route element={<Workspaces />} path="/workspace" />
      <Route element={<Workspace />} path="/workspace/:id" />
      <Route element={<Create />} path="/create" />
    </Routes>
  );
}

export default App;
