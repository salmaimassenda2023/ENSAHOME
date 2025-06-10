"use client";

import { useEffect, useState, useRef } from "react";
import { useProfileContext } from "@/app/profil/layout";
import { FaRegPlusSquare, FaTimes } from "react-icons/fa";
import EquipementCard from "@/components/ui/EquipementCard";
import LogementCard from "@/components/ui/LogementCard";
import EquipementForm from "@/components/ui/EquipementForm";
import LogementForm from "@/components/ui/LogementForm";
import Link from "next/link";

export default function PublicationsPage() {
    const { setActiveTab } = useProfileContext();
    const [activeFilter, setActiveFilter] = useState("tous");
    const [showActionMenu, setShowActionMenu] = useState(false);
    const actionMenuRef = useRef(null);

    // États pour la gestion des modals
    const [showEditModal, setShowEditModal] = useState(false);
    const [editingItem, setEditingItem] = useState(null);
    const [editFormType, setEditFormType] = useState(null);

    const [publications, setPublications] = useState([]);

    useEffect(() => {
        setActiveTab("publications");

        const token = localStorage.getItem("token"); // ou sessionStorage.getItem("token")

        fetch(`http://localhost:8081/publications`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`, // Ajout du token ici
            },
        })
            .then((res) => {
                if (!res.ok) throw new Error("Erreur lors du chargement des publications");
                return res.json();
            })
            .then((data) => {
                setPublications(data);
            })
            .catch((error) => {
                console.error("Erreur de chargement des publications:", error);
            });
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

    // Fonction pour gérer le double-clic sur une card
    const handleDoubleClick = (item) => {
        setEditingItem(item);
        setEditFormType(item.typePub);
        setShowEditModal(true);
    };

    // Fonction pour fermer le modal
    const handleCloseModal = () => {
        setShowEditModal(false);
        setEditingItem(null);
    };

    // Fonction pour traiter la soumission du formulaire de modification
    const handleEditSubmit = (updatedData) => {
        // Ici, vous implémenteriez la logique pour mettre à jour vos données
        console.log("Données mises à jour:", updatedData);

        // Par exemple, vous pourriez mettre à jour votre état local ou envoyer à une API
        // updatePublication(editingItem.id, updatedData);

        // Fermer le modal après la soumission
        handleCloseModal();
    };

    return (
        <div className="relative min-h-screen">
            {/* Floating Action Button avec menu déroulant */}
            <div className="flex justify-end" ref={actionMenuRef}>
                <button
                    onClick={() => setShowActionMenu(!showActionMenu)}
                    className="text-3xl transition-all hover:shadow-lg hover:bg-gray-200 cursor-pointer"
                >
                    <FaRegPlusSquare />
                </button>

                {/* Menu d'action qui apparaît lors du clic */}
                {showActionMenu && (
                    <div className="absolute top-12 right-0 bg-white shadow-lg rounded-lg overflow-hidden w-[180px] z-30">
                        <div className="w-full text-left px-4 py-3 hover:bg-gray-100 transition-colors border-b">
                            <Link href="/profil/publications/logementForm">logement</Link>
                        </div>
                        <div className="w-full text-left px-4 py-3 hover:bg-gray-100 transition-colors">
                            <Link href="/profil/publications/EquipementForm">équipement</Link>
                        </div>
                    </div>
                )}
            </div>

            {/* Onglets de filtre */}
            <div className="flex gap-2 mb-6 overflow-x-auto">
                <button
                    onClick={() => setActiveFilter("tous")}
                    className={`px-6 py-2 rounded-full cursor-pointer ${
                        activeFilter === "tous" ? "bg-green-400 hover:shadow-lg hover:bg-green-300" : "bg-gray-100 hover:shadow-lg hover:bg-gray-200 "
                    }`}
                >
                    Tous
                </button>
                <button
                    onClick={() => setActiveFilter("logements")}
                    className={`px-6 py-2 rounded-full cursor-pointer ${
                        activeFilter === "logements" ? "bg-green-400 hover:shadow-lg hover:bg-green-300" : "bg-gray-100 hover:shadow-lg hover:bg-gray-200 "
                    }`}
                >
                    Logements
                </button>
                <button
                    onClick={() => setActiveFilter("equipements")}
                    className={`px-6 py-2 rounded-full cursor-pointer ${
                        activeFilter === "equipements" ? "bg-green-400 hover:shadow-lg hover:bg-green-300" : "bg-gray-100 hover:shadow-lg hover:bg-gray-200 "
                    }`}
                >
                    Équipements
                </button>
            </div>

            {/* Liste des publications filtrées */}
            <div className={`grid grid-cols-1 lg:grid-cols-2 gap-6 transition-all duration-300 ${showEditModal ? 'blur-sm' : ''}`}>
                {filteredPublications.map((item) =>
                    item.typePub === "logements" ? (
                        <div key={item.id} onDoubleClick={() => handleDoubleClick(item)}>
                            <LogementCard
                                id={item.id}
                                photos={"/"+item.photos[0]}
                                nbresPiece={item.nombrePieces}
                                loyer={item.loyer}
                                type={item.type}
                                commodites={item.commodites}
                            />
                        </div>
                    ) : (
                        <div key={item.id} onDoubleClick={() => handleDoubleClick(item)}>
                            <EquipementCard
                                id={item.id}
                                photos={"/"+item.photos[0]}
                                designation={item.designation}
                                prix={item.prix}
                            />
                        </div>
                    )
                )}
            </div>

            {/* Option 2: Hauteur conditionnelle selon le type de formulaire */}
            {showEditModal && (
                <div className="fixed inset-0 flex items-center justify-center z-50 p-2 pointer-events-none">
                    <div
                        className={`bg-white rounded-lg max-w-4xl w-full overflow-hidden shadow-2xl pointer-events-auto transform transition-all duration-300 ease-out animate-in ${
                            editFormType === "logements" ? "max-h-[98vh]" : "max-h-[85vh]"
                        }`}
                        style={{
                            boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.25), 0 0 0 1px rgba(0, 0, 0, 0.05)'
                        }}
                    >
                        <div className="flex justify-end border-b border-gray-200 bg-gray-50">
                            <button
                                onClick={handleCloseModal}
                                className="text-gray-600 hover:text-gray-600 transition-colors p-2 rounded-full hover:bg-gray-200"
                            >
                                <FaTimes size={25} />
                            </button>
                        </div>

                        <div className={`p-6 overflow-y-auto ${
                            editFormType === "logements"
                                ? "max-h-[calc(98vh-70px)]"
                                : "max-h-[calc(85vh-70px)]"
                        }`}>
                            {editFormType === "logements" ? (
                                <LogementForm
                                    initialData={editingItem}
                                    onSubmit={handleEditSubmit}
                                    isEditing={true}
                                />
                            ) : (
                                <EquipementForm
                                    initialData={editingItem}
                                    onSubmit={handleEditSubmit}
                                    isEditing={true}
                                />
                            )}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}