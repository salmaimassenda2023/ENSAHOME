import { useState } from "react";

export default function LogementForm({ onClose }) {
    const [formData, setState] = useState({
        type: "Appartement",
        nombrePieces: "",
        ville: "",
        quartier: "",
        loyer: "",
        description: "",
        telephone: "",
        commodites: {
            wifi: false,
            parking: false,
            ascenseur: false,
            climatisation: false
        },
        photos: []
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setState(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleCommoditeChange = (e) => {
        const { name, checked } = e.target;
        setState(prev => ({
            ...prev,
            commodites: {
                ...prev.commodites,
                [name]: checked
            }
        }));
    };

    const handlePhotoUpload = (e) => {
        // Handle photo uploads
        const files = Array.from(e.target.files);
        // Preview could be added here
        setState(prev => ({
            ...prev,
            photos: files
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Here you would typically:
        // 1. Validate the data
        // 2. Format it if needed
        // 3. Send to your API
        console.log("Form submitted:", formData);

        // Close the form
        onClose();
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4">
            <div>
                <label className="block text-sm font-medium text-gray-700">Type de logement</label>
                <select
                    name="type"
                    value={formData.type}
                    onChange={handleChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                >
                    <option value="Appartement">Appartement</option>
                    <option value="Studio">Studio</option>
                    <option value="Maison">Maison</option>
                    <option value="Chambre">Chambre</option>
                </select>
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">Nombre de pièces</label>
                <input
                    type="number"
                    name="nombrePieces"
                    value={formData.nombrePieces}
                    onChange={handleChange}
                    min="1"
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                    required
                />
            </div>

            <div className="grid grid-cols-2 gap-4">
                <div>
                    <label className="block text-sm font-medium text-gray-700">Ville</label>
                    <input
                        type="text"
                        name="ville"
                        value={formData.ville}
                        onChange={handleChange}
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                        required
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium text-gray-700">Quartier</label>
                    <input
                        type="text"
                        name="quartier"
                        value={formData.quartier}
                        onChange={handleChange}
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                        required
                    />
                </div>
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">Loyer (DH/mois)</label>
                <input
                    type="number"
                    name="loyer"
                    value={formData.loyer}
                    onChange={handleChange}
                    min="0"
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                    required
                />
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">Commodités</label>
                <div className="mt-1 grid grid-cols-2 gap-4">
                    <div className="flex items-center">
                        <input
                            type="checkbox"
                            id="wifi"
                            name="wifi"
                            checked={formData.commodites.wifi}
                            onChange={handleCommoditeChange}
                            className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                        />
                        <label htmlFor="wifi" className="ml-2 block text-sm text-gray-700">WiFi</label>
                    </div>
                    <div className="flex items-center">
                        <input
                            type="checkbox"
                            id="parking"
                            name="parking"
                            checked={formData.commodites.parking}
                            onChange={handleCommoditeChange}
                            className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                        />
                        <label htmlFor="parking" className="ml-2 block text-sm text-gray-700">Parking</label>
                    </div>
                    <div className="flex items-center">
                        <input
                            type="checkbox"
                            id="ascenseur"
                            name="ascenseur"
                            checked={formData.commodites.ascenseur}
                            onChange={handleCommoditeChange}
                            className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                        />
                        <label htmlFor="ascenseur" className="ml-2 block text-sm text-gray-700">Ascenseur</label>
                    </div>
                    <div className="flex items-center">
                        <input
                            type="checkbox"
                            id="climatisation"
                            name="climatisation"
                            checked={formData.commodites.climatisation}
                            onChange={handleCommoditeChange}
                            className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                        />
                        <label htmlFor="climatisation" className="ml-2 block text-sm text-gray-700">Climatisation</label>
                    </div>
                </div>
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">Description</label>
                <textarea
                    name="description"
                    value={formData.description}
                    onChange={handleChange}
                    rows="4"
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                    required
                ></textarea>
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">Téléphone de contact</label>
                <input
                    type="tel"
                    name="telephone"
                    value={formData.telephone}
                    onChange={handleChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                    required
                />
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">Photos du logement</label>
                <input
                    type="file"
                    multiple
                    accept="image/*"
                    onChange={handlePhotoUpload}
                    className="mt-1 block w-full"
                    required
                />
            </div>

            <div className="flex gap-4 justify-end">
                <button
                    type="button"
                    onClick={onClose}
                    className="px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50"
                >
                    Annuler
                </button>
                <button
                    type="submit"
                    className="px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700"
                >
                    Partager
                </button>
            </div>
        </form>
    );
}