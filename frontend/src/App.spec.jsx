import { render, screen } from "@testing-library/react";
import App from "./App";
describe("<App /> Test component rendering", () => {
  it("should display elements of the App component", () => {
    render(<App />);
    // screen.debug();
  });
});
