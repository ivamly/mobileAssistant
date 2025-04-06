import workSpaces from "@/mock";
import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { configureStore } from "@reduxjs/toolkit";

import { setupListeners } from "@reduxjs/toolkit/query";
export const api = createApi({
  reducerPath: "api",
  tagTypes: ["Transcript"],
  baseQuery: fetchBaseQuery({ baseUrl: "http://localhost:8080" }),
  endpoints: (build) => ({
    getTranscripts: build.query<typeof workSpaces, void>({
      providesTags: ["Transcript"],
      query: () => `/api/v1/records`,
    }),
    getTranscript: build.query<(typeof workSpaces)[number], { id: string }>({
      query: ({ id }) => `records/${id}`,
      providesTags: ["Transcript"],
    }),
    sendToEmail: build.query({
      query: (data) => ({
        url: `sendOnEmail`,
        method: "POST",
        body: data,
      }),
    }),
    uploadFile: build.query({
      query: (data) => ({
        url: `files/upload`,
        method: "POST",
        body: data,
      }),
    }),
    editSpeeckerNames: build.mutation({
      query: (data) => ({
        url: `participants/updateName`,
        method: "PATCH",
        body: data,
      }),
      invalidatesTags: ["Transcript"],
    }),
  }),
});

export const {
  useGetTranscriptsQuery,
  useEditSpeeckerNamesMutation,
  useGetTranscriptQuery,
  useSendToEmailQuery,
  useLazyUploadFileQuery,
  useLazySendToEmailQuery,
} = api;

export const store = configureStore({
  reducer: {
    [api.reducerPath]: api.reducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(api.middleware),
});

setupListeners(store.dispatch);
