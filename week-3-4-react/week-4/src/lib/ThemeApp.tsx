import { createContext, useContext, useState, type ReactNode } from "react";

type Theme = "light" | "dark";
interface ThemeContextType {
    theme: Theme;
    setTheme: (theme: Theme) => void;
}

const ThemeContext = createContext<ThemeContextType | undefined>(undefined);
function ThemeProvider({ children }: { children: ReactNode }) {
    const [theme, setTheme] = useState<Theme>("light");
    return (
        <ThemeContext.Provider value={{ theme, setTheme }}>
            <div className={theme === "light" ? "theme-light" : "theme-dark"}>
                {children}
            </div>
        </ThemeContext.Provider>
    );
}

function useTheme() {
    const context = useContext(ThemeContext);
    if (!context) throw new Error("no context");
    return context;
}


function ThemeSelector() {
    const { theme, setTheme } = useTheme();
    return (
        <div style={{ marginBottom: 16 }}>
            <span>Current theme: <b>{theme}</b></span>
            <button onClick={() => setTheme("light")} style={{ marginLeft: 8 }}>
                Light
            </button>
            <button onClick={() => setTheme("dark")} style={{ marginLeft: 8 }}>
                Dark
            </button>
        </div>
    );
}

function ThemedContent() {
    const { theme } = useTheme();
    return (
        <div>
            <h2>{theme === "light" ? "🌞 Light Mode" : "🌙 Dark Mode"}</h2>
            <p>
                This content changes style based on the selected theme.
            </p>
        </div>
    );
}

function ThemeApp() {
    return (
        <ThemeProvider>
            <ThemeSelector />
            <ThemedContent />
            <style>{`
                .theme-light {
                    background: #fff;
                    color: #222;
                }
                .theme-dark {
                    background: #222;
                    color: #fff;
                    min-height: 100vh;
                }
                .theme-dark button {
                    background: #444;
                    color: #fff;
                }
                body {
                    padding: 0;
                    margin: 0;
                }
            `}</style>
        </ThemeProvider>
    );
}

export default ThemeApp;