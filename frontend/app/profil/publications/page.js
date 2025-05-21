"use client";

import { useEffect, useState, useRef } from "react";
import { useProfileContext } from "@/app/profil/layout";
import { FaRegPlusSquare } from "react-icons/fa";
import { logements, publications } from "@/data";
import EquipementCard from "@/components/ui/EquipementCard";
import LogementCard from "@/components/ui/LogementCard";
import LogementForm from "@/components/ui/LogementForm";
import Link from "next/link";



export default function PublicationsPage() {
    const { setActiveTab } = useProfileContext();
    const [activeFilter, setActiveFilter] = useState("tous");
    const [showActionMenu, setShowActionMenu] = useState(false);
    const [showForm, setShowForm] = useState(false);
    const [formType, setFormType] = useState(null); // "logements" or "equipements"
    const actionMenuRef = useRef(null);

    // Mettre à jour l'onglet actif lorsque la page est chargée
    useEffect(() => {
        setActiveTab("publications");
    }, [setActiveTab]);

    // Fermer le menu d'action lorsqu'on clique à l'extérieur
    useEffect(() => {
        function handleClickOutside(event) {
            if (actionMenuRef.current && !actionMenuRef.current.contains(event.target)) {
                setShowActionMenu(false);
            }
        }

        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

    // Filtrer les publications en fonction du filtre actif
    const filteredPublications =
        activeFilter === "tous"
            ? publications
            : publications.filter((pub) => pub.typePub === activeFilter);

    // Ouvrir le formulaire correspondant
    const handleOpenForm = (type) => {
        setFormType(type);
        setShowForm(true);
        setShowActionMenu(false);
    };



    function handleOpenFormLogement(logements1) {


    }

    function handleOpenFormEquip(equipements) {

    }

    return (
        <div >
            {/* Floating Action Button avec menu déroulant */}
            <div className="flex justify-end" ref={actionMenuRef}>
                <button
                    onClick={() => setShowActionMenu(!showActionMenu)}
                    className="text-3xl  transition-all"
                >
                    <FaRegPlusSquare />
                </button>

                {/* Menu d'action qui apparaît lors du clic */}
                {showActionMenu && (
                    <div className="absolute top-50 right-42 bg-white shadow-lg rounded-lg overflow-hidden w-[180px]">
                        <div className="w-full text-left px-4 py-3 hover:bg-gray-100 transition-colors border-b">
                            <Link
                                href="/profil/publications/logementForm"

                            >
                                logement
                            </Link>
                        </div>

                        <button
                            onClick={() => handleOpenFormEquip()}
                            className="w-full text-left px-4 py-3 hover:bg-gray-100 transition-colors"
                        >
                            équipement
                        </button>
                    </div>
                )}
            </div>



            {/* Onglets de filtre */}
            <div className="flex gap-2 mb-6 overflow-x-auto">
                <button
                    onClick={() => setActiveFilter("tous")}
                    className={`px-6 py-2 rounded-full ${
                        activeFilter === "tous" ? "bg-green-400" : "bg-gray-100"
                    }`}
                >
                    Tous
                </button>
                <button
                    onClick={() => setActiveFilter("logements")}
                    className={`px-6 py-2 rounded-full ${
                        activeFilter === "logements" ? "bg-green-400" : "bg-gray-100"
                    }`}
                >
                    Logements
                </button>
                <button
                    onClick={() => setActiveFilter("equipements")}
                    className={`px-6 py-2 rounded-full ${
                        activeFilter === "equipements" ? "bg-green-400" : "bg-gray-100"
                    }`}
                >
                    Équipements
                </button>
            </div>

            {/* Liste des publications filtrées */}
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
                {filteredPublications.map((item) =>
                    item.typePub === "logements" ? (
                        <LogementCard
                            key={item.id}
                            id={item.id}
                            photos={item.photos[0]}
                            nbresPiece={item.nombrePieces}
                            loyer={item.loyer}
                            type={item.type}
                            commodites={item.commodites}
                        />
                    ) : (
                        <EquipementCard
                            key={item.id}
                            id={item.id}
                            photos={item.photos[0]}
                            designation={item.designation}
                            prix={item.prix}
                        />
                    )
                )}
            </div>
        </div>
    );
}