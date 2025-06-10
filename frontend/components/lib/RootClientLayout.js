"use client";

import Header from "@/components/layout/Header";
import { useEffect, useState } from "react";
import { useRouter, usePathname } from "next/navigation";

export default function RootClientLayout({ children }) {
  const [loading, setLoading] = useState(true);
  const router = useRouter();
  const pathname = usePathname();

  useEffect(() => {
    const token = localStorage.getItem("token");

    // ❌ Non connecté → Redirige vers /login (sauf si déjà sur /login)
    if (!token && pathname !== "/login" && pathname !== "/register") {
      router.push("/login");
      return;
    }

    // ✅ Déjà connecté → Ne doit pas aller sur /login, donc on redirige vers /
    if (token && (pathname === "/login" || pathname === "/register")) {
      router.push("/");
      return;
    }

    // ✅ Bon état : afficher la page
    setLoading(false);
  }, [pathname, router]);

  if (loading) return null;

  return (
    <div>
      {pathname !== "/login" && pathname !== "/register" && <Header />}
      {children}
    </div>
  );
}