'use client'

import React, { useState } from 'react';
import { User, Mail, Lock, Home } from 'lucide-react';
import Image from "next/image";

export default function LoginPage() {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Login attempt:', formData);
        // Ici vous pouvez ajouter la logique de connexion
    };

    return (
        <div className="h-150 bg-gradient-to-br from-purple-100 via-pink-50 to-blue-100 flex items-center justify-center m-15">
            {/* Section gauche avec le logo */}
            <div className="flex-1 flex items-center justify-center p-8">
                <div className="text-center">
                    {/* Logo ENSAHome */}
                    <div className="mb-8">
                        <div className="inline-flex items-center justify-center rounded-2xl mb-4">
                            <Image
                            src="/logo-2.png"
                            alt="logo"
                            width={350}
                            height={350}
                            />
                        </div>

                    </div>
                </div>
            </div>

            {/* Section droite avec le formulaire */}
            <div className="w-100 bg-white/80 backdrop-blur-sm border-l border-white/20 flex flex-col h-150">


                {/* Formulaire de connexion */}
                <div className="flex-1 flex flex-col justify-center p-8">
                    <div className="mb-8">
                        <h2 className="text-xl font-medium text-gray-700 mb-2 text-center">
                            Login into your account
                        </h2>
                    </div>

                    <form onSubmit={handleSubmit} className="space-y-4">
                        {/* Champ Nom */}
                        <div className="relative">
                            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <User className="h-5 w-5 text-gray-400" />
                            </div>
                            <input
                                type="text"
                                name="name"
                                placeholder="Name"
                                value={formData.name}
                                onChange={handleInputChange}
                                className="w-full pl-10 pr-4 py-3 border border-gray-200 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all bg-white/70 backdrop-blur-sm"
                            />
                        </div>

                        {/* Champ Email */}
                        <div className="relative">
                            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <Mail className="h-5 w-5 text-gray-400" />
                            </div>
                            <input
                                type="email"
                                name="email"
                                placeholder="E-mail"
                                value={formData.email}
                                onChange={handleInputChange}
                                className="w-full pl-10 pr-4 py-3 border border-gray-200 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all bg-white/70 backdrop-blur-sm"
                            />
                        </div>

                        {/* Champ Mot de passe */}
                        <div className="relative">
                            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <Lock className="h-5 w-5 text-gray-400" />
                            </div>
                            <input
                                type="password"
                                name="password"
                                placeholder="Password"
                                value={formData.password}
                                onChange={handleInputChange}
                                className="w-full pl-10 pr-4 py-3 border border-gray-200 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all bg-white/70 backdrop-blur-sm"
                            />
                        </div>

                        {/* Bouton de connexion */}
                        <button
                            type="submit"
                            className="w-full bg-gray-700 hover:bg-gray-800 text-white font-medium py-3 px-4 rounded-lg transition-colors duration-200 mt-6"
                        >
                            Login now
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
}