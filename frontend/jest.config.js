module.exports = {
  testEnvironment: "jest-environment-jsdom",
  setupFilesAfterEnv: ["<rootDir>/.jest/setup-tests.js"],
  moduleNameMapper: {
    "\\.(png|jpg|jpeg|gif|svg)$": "<rootDir>.jest/mocks/fileMock.js",
    "\\.(css|less|sass|scss)$": "identity-obj-proxy",
    "^react-google-recaptcha$": "<rootDir>.jest/mocks/react-google-recaptcha.js",
  },
};
