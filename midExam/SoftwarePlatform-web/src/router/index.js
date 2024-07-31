import { useRoutes } from "react-router-dom";
import routes from "./routes";
import { Suspense } from "react";
import FullScreenLoading from "../components/fullScreenLoading";
export default function Router() {
  return (
    <Suspense fallback={<FullScreenLoading />}>{useRoutes(routes)}</Suspense>
  );
}
