"use client";

import { useEffect, useState } from "react";
import LogementCard from "@/components/ui/LogementCard";

export default function LogementsPage() {
  const [publications, setPublications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    // S'assurer que l'on est bien côté client (pour localStorage)
    if (typeof window === "undefined") return;

    const token = localStorage.getItem("token");
    if (!token) {
      setError("Utilisateur non authentifié");
      setLoading(false);
      return;
    }

    fetch("http://localhost:8081/publications/ville", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        if (!res.ok) throw new Error("Erreur lors du chargement des publications");
        return res.json();
      })
      .then((data) => {
        setPublications(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Erreur de chargement des publications:", err);
        setError("Impossible de charger les publications");
        setLoading(false);
      });
  }, []);

  return (
    <div className="mt-20 mb-20 mr-30 ml-30">
      {/* Titre */}
      <div className="text-center">
        <h1 className="text-3xl leading-tight text-green-500">
          Explorez les logements disponibles près de votre campus
        </h1>
      </div>

      {/* Sous-titre */}
      <div className="text-center m-10">
        <h2 className="text-3xl leading-tight text-gray-500">
          Biens immobiliers à louer
        </h2>
      </div>

      {/* Chargement / Erreur */}
      {loading && <p className="text-center text-gray-400">Chargement...</p>}
      {error && <p className="text-center text-red-500">{error}</p>}

      {/* Liste de logements */}
      {!loading && !error && (
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-15">
          {publications.length === 0 ? (
            <p className="text-center text-gray-500">Aucune publication trouvée.</p>
          ) : (
            publications.map((item) => (
              <LogementCard
                key={item.id}
                id={item.id}
                photos={"/"+item.photos?.[0]}
                nbresPiece={item.nombrePieces}
                loyer={item.loyer}
                type={item.type}
                commodites={item.commodites}
              />
            ))
          )}
        </div>
      )}
    </div>
  );
}
