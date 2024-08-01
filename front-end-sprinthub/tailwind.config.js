// tailwind.config.js
const { nextui } = require("@nextui-org/react");

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  darkMode: "class",
  plugins: [
    nextui({
      prefix: "nextui", // prefix for themes variables
      addCommonColors: false, // override common colors (e.g. "blue", "green", "pink").
      defaultTheme: "light", // default theme from the themes object
      defaultExtendTheme: "light", // default theme to extend on custom themes
      layout: {}, // common layout tokens (applied to all themes)
      themes: {
        light: {
          layout: {}, // light theme layout tokens
          colors: {
            fund: "#ffffff",
            mainText: "#000000",
            primaryButton: "#f7971d",
            containerEdges: "#B0B0B0",
            secondaryButton: "#003366",
            successIndicator: "#388E3C",
            errorIndicator: "#C62828",
          },
        },
        dark: {
          layout: {},
          colors: {
            fund: "#242526",
            mainText: "#FFFFFF",
            primaryButton: "#f7971d",
            containerEdges: "#565758",
            secondaryButton: "#003366",
            successIndicator: "#8BC34A",
            errorIndicator: "#D32F2F",
          },
        },
      },
    }),
  ],
};
