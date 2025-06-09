'use client'

import React, { useState } from 'react';
import { Home, Upload, ChevronDown } from 'lucide-react';
import Image from "next/image";

export default function SignUpComponent() {
    const [activeRole, setActiveRole] = useState('students'); // 'proprietaire' ou 'students'
    const [formData, setFormData] = useState({
        nom: '',
        prenom: '',
        email: '',
        mobile: '',
        anneeEtudes: '',
        villeEcole: '',
        pieceIdentite: null,
        villeProprietaire: '',
        password: '',
        confirmPassword: '',
        acceptConditions: false
    });

    const handleInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    const handleFileUpload = (e) => {
        const file = e.target.files[0];
        setFormData(prev => ({
            ...prev,
            pieceIdentite: file
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Sign up attempt:', { role: activeRole, data: formData });
    };

    const anneesEtudes = [
        'Select',
        '1ère année',
        '2ème année',
        '3ème année',
        '4ème année',
        '5ème année',
        'Master 1',
        'Master 2',
        'Doctorat'
    ];

    return (
        <div className="h-167 bg-gradient-to-br from-purple-100 via-pink-50 to-blue-100 w-260 ">

            <div className="flex max-w-6xl mx-auto px-4 gap-8 ">
                {/* Section logo */}
                <div className="w-120 flex items-center justify-center">
                    <div className="inline-flex items-center justify-center rounded-2xl mb-4">
                        <Image
                            src="/logo-2.png"
                            alt="logo"
                            width={350}
                            height={350}
                        />
                    </div>
                </div>
            {/*    */}
                <div >
                    {/* Header avec navigation des rôles */}
                    <div className="pt-4 px-4">
                        <div className="max-w-4xl mx-auto">
                            <div className="bg-black rounded-lg p-1 flex items-center mb-6">


                                <div className="flex flex-1">
                                    <button
                                        onClick={() => setActiveRole('proprietaire')}
                                        className={`flex-1 py-2 px-6 text-center transition-all duration-200 ${
                                            activeRole === 'proprietaire'
                                                ? 'bg-white text-black rounded-lg font-medium'
                                                : 'text-white hover:text-gray-200'
                                        }`}
                                    >
                                        Propriétaire
                                    </button>


                                    <button
                                        onClick={() => setActiveRole('students')}
                                        className={`flex-1 py-2 px-6 text-center transition-all duration-200 ${
                                            activeRole === 'students'
                                                ? 'bg-white text-black rounded-lg font-medium'
                                                : 'text-white hover:text-gray-200'
                                        }`}
                                    >
                                        Étudiants
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    {/* Section formulaire */}
                    <div className="flex-1 bg-white/80 backdrop-blur-sm rounded-2xl p-8 shadow-lg">
                        <form onSubmit={handleSubmit} className="space-y-6">
                            {/* Nom et Prénom */}
                            <div className="grid grid-cols-2 gap-4">
                                <div>
                                    <label className="block text-sm font-medium text-gray-700 mb-2">
                                        Nom :
                                    </label>
                                    <input
                                        type="text"
                                        name="nom"
                                        placeholder="Enter your name..."
                                        value={formData.nom}
                                        onChange={handleInputChange}
                                        className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all"
                                    />
                                </div>
                                <div>
                                    <label className="block text-sm font-medium text-gray-700 mb-2">
                                        Prénom :
                                    </label>
                                    <input
                                        type="text"
                                        name="prenom"
                                        placeholder="Enter your name..."
                                        value={formData.prenom}
                                        onChange={handleInputChange}
                                        className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all"
                                    />
                                </div>
                            </div>

                            {/* Email et Mobile */}
                            <div className="grid grid-cols-2 gap-4">
                                <div>
                                    <label className="block text-sm font-medium text-gray-700 mb-2">
                                        {activeRole === 'students' ? 'Email :' : 'Email institutionnel :'}
                                    </label>
                                    <input
                                        type="email"
                                        name="email"
                                        placeholder={activeRole === 'students' ? 'info@xyz.com' : 'Ex: name@ensa-khouribga.ac.ma'}
                                        value={formData.email}
                                        onChange={handleInputChange}
                                        className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all"
                                    />
                                </div>
                                <div>
                                    <label className="block text-sm font-medium text-gray-700 mb-2">
                                        Mobile No. :
                                    </label>
                                    <input
                                        type="tel"
                                        name="mobile"
                                        placeholder="+91 - 98596 55000"
                                        value={formData.mobile}
                                        onChange={handleInputChange}
                                        className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all"
                                    />
                                </div>
                            </div>

                            {/* Champs spécifiques selon le rôle */}
                            {activeRole === 'students' ? (
                                <div className="grid grid-cols-2 gap-4">
                                    <div>
                                        <label className="block text-sm font-medium text-gray-700 mb-2">
                                            Année d'études :
                                        </label>
                                        <div className="relative">
                                            <select
                                                name="anneeEtudes"
                                                value={formData.anneeEtudes}
                                                onChange={handleInputChange}
                                                className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all appearance-none cursor-pointer"
                                            >
                                                {anneesEtudes.map((annee) => (
                                                    <option key={annee} value={annee}>
                                                        {annee}
                                                    </option>
                                                ))}
                                            </select>
                                            <ChevronDown className="absolute right-3 top-1/2 transform -translate-y-1/2 h-5 w-5 text-gray-400 pointer-events-none" />
                                        </div>
                                    </div>
                                    <div>
                                        <label className="block text-sm font-medium text-gray-700 mb-2">
                                            Ville de l'école :
                                        </label>
                                        <div className="relative">
                                            <select
                                                name="villeEcole"
                                                value={formData.villeEcole}
                                                onChange={handleInputChange}
                                                className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all appearance-none cursor-pointer"
                                            >
                                                <option value="">Select</option>
                                                <option value="khouribga">Khouribga</option>
                                                <option value="casablanca">Casablanca</option>
                                                <option value="rabat">Rabat</option>
                                                <option value="fes">Fès</option>
                                                <option value="marrakech">Marrakech</option>
                                            </select>
                                            <ChevronDown className="absolute right-3 top-1/2 transform -translate-y-1/2 h-5 w-5 text-gray-400 pointer-events-none" />
                                        </div>
                                    </div>
                                </div>
                            ) : (
                                <div className="grid grid-cols-2 gap-4">
                                    <div>
                                        <label className="block text-sm font-medium text-gray-700 mb-2">
                                            Pièce d'identité :
                                        </label>
                                        <div className="relative">
                                            <input
                                                type="file"
                                                id="pieceIdentite"
                                                name="pieceIdentite"
                                                onChange={handleFileUpload}
                                                className="hidden"
                                                accept=".pdf,.jpg,.jpeg,.png"
                                            />
                                            <label
                                                htmlFor="pieceIdentite"
                                                className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg cursor-pointer flex items-center text-gray-500 hover:bg-gray-200 transition-all"
                                            >
                                                <Upload className="h-5 w-5 mr-2" />
                                                {formData.pieceIdentite ? formData.pieceIdentite.name : 'Téléchargement d\'une pièce d\'identité'}
                                            </label>
                                        </div>
                                    </div>
                                    <div>
                                        <label className="block text-sm font-medium text-gray-700 mb-2">
                                            Ville de propriétaire :
                                        </label>
                                        <input
                                            type="text"
                                            name="villeProprietaire"
                                            placeholder=""
                                            value={formData.villeProprietaire}
                                            onChange={handleInputChange}
                                            className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all"
                                        />
                                    </div>
                                </div>
                            )}

                            {/* Mots de passe */}
                            <div className="grid grid-cols-2 gap-4">
                                <div>
                                    <label className="block text-sm font-medium text-gray-700 mb-2">
                                        Password :
                                    </label>
                                    <input
                                        type="password"
                                        name="password"
                                        placeholder="xxxxxxxxx"
                                        value={formData.password}
                                        onChange={handleInputChange}
                                        className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all"
                                    />
                                </div>
                                <div>
                                    <label className="block text-sm font-medium text-gray-700 mb-2">
                                        Confirm Password :
                                    </label>
                                    <input
                                        type="password"
                                        name="confirmPassword"
                                        placeholder="xxxxxxxxx"
                                        value={formData.confirmPassword}
                                        onChange={handleInputChange}
                                        className="w-full px-4 py-3 bg-gray-100 border-0 rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all"
                                    />
                                </div>
                            </div>

                            {/* Conditions d'utilisation */}
                            <div className="flex items-center">
                                <input
                                    type="checkbox"
                                    id="acceptConditions"
                                    name="acceptConditions"
                                    checked={formData.acceptConditions}
                                    onChange={handleInputChange}
                                    className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                                />
                                <label htmlFor="acceptConditions" className="ml-2 text-sm text-gray-700">
                                    J'accepte les conditions d'utilisation
                                </label>
                            </div>

                            {/* Bouton de soumission */}
                            <button
                                type="submit"
                                className="w-full bg-gray-800 hover:bg-gray-900 text-white font-medium py-3 px-4 rounded-lg transition-colors duration-200"
                            >
                                Sign up
                            </button>
                        </form>
                    </div>
                </div>


            </div>
        </div>
    );
}