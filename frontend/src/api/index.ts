import workSpaces from "@/mock";
import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { configureStore } from "@reduxjs/toolkit";

import { setupListeners } from "@reduxjs/toolkit/query";
export const api = createApi({
  reducerPath: "api",
  tagTypes: ["Transcript"],
  baseQuery: fetchBaseQuery({ baseUrl: "http://localhost:8080/api/v1/" }),
  endpoints: (build) => ({
    getTranscripts: build.query<typeof workSpaces, void>({
      providesTags: ["Transcript"],
      query: () => `transcripts`,
    }),
    getTranscript: build.query<(typeof workSpaces)[number], { id: string }>({
      query: ({ id }) => `transcripts/${id}`,
      providesTags: ["Transcript"],
    }),
    sendToEmail: build.query({
      query: ({ id, data }) => ({
        url: `email/${id}`,
        method: "PUT",
        body: data,
      }),
    }),
    uploadFile: build.query({
      query: ({ id, data }) => ({
        url: `files/upload/${id}`,
        method: "PUT",
        body: data,
      }),
    }),
    editSpeeckerNames: build.mutation({
      query: ({ id, data }) => ({
        url: `speeckers/${id}`,
        method: "PUT",
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
