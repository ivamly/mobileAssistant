import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

import App from "./App.tsx";
import { Provider } from "./provider.tsx";
import "@/styles/globals.css";
import { ApiProvider } from "@reduxjs/toolkit/query/react";
import { api } from "./api/index.ts";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <BrowserRouter>
      <ApiProvider api={api}>
        <Provider>
          <App />
        </Provider>
      </ApiProvider>
    </BrowserRouter>
  </React.StrictMode>
);
