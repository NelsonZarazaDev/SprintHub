"use client";

import { useTheme } from "next-themes";
import { useEffect, useState } from "react";
import { GoSun } from "react-icons/go";
import { GoMoon } from "react-icons/go";

export function ThemeSwitcher() {
  const [mounted, setMounted] = useState(false);
  const { theme, setTheme } = useTheme();

  useEffect(() => {
    setMounted(true);
  }, []);

  if (!mounted) return null;

  return (
    <div className="flex space-x-4">
      <button
        className="w-6 rounded-sm h-auto p-1 bg-slate-200 dark:bg-slate-700"
        onClick={() => setTheme("light")}
      >
        <GoSun />
      </button>
      <button
        className="w-6 rounded-sm h-auto p-1 bg-slate-200 dark:bg-slate-700"
        onClick={() => setTheme("dark")}
      >
        <GoMoon />
      </button>
    </div>
  );
}
