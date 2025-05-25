"use client"; // Nécessaire pour utiliser les hooks et les événements

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import Input from "@/components/ui/Input";
import { FaGoogle } from "react-icons/fa";
import Header from '@/components/layout/Header';
import Link from 'next/link';


export default function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const router = useRouter();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email,
          password
        }),
        credentials: 'include' // Si vous utilisez des cookies/sessions
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || 'Login failed');
      }

      // ✅ Enregistrer le token dans localStorage
      localStorage.setItem('token', data.token); // Assurez-vous que votre backend renvoie un champ "token"
      // Si la connexion réussit, redirigez vers la page d'accueil
      router.push('/');
    } catch (err) {
      setError(err.message || 'An error occurred during login');
    }
  };

  return (
    <div>

        <Header />
      <div className="min-h-screen bg-gray-50 flex flex-col justify-center py-12 sm:px-6 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        <h1 className="text-center text-3xl font-bold text-gray-900 mb-2">ENSAHome</h1>
        <h2 className="text-center text-xl font-semibold text-gray-600">
          Login into your account
        </h2>
      </div>

      <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
          <form onSubmit={handleSubmit} className="space-y-6">
        <Input label="E-mail" id="email" name="email" type="email" requiredvalue={email}onChange={(e) => setEmail(e.target.value)} />
        <Input label="Password" id="password" name="password" type="password" requiredvalue={password}onChange={(e) => setPassword(e.target.value)} />

        {error && <p className="text-red-500 text-sm">{error}</p>}

        <button
          type="submit"
          className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
        >
          Login now
        </button>
      </form>

          <div className="mt-6">
            <div className="relative">
              <div className="absolute inset-0 flex items-center">
                <div className="w-full border-t border-gray-300"></div>
              </div>
              <div className="relative flex justify-center text-sm">
                <span className="px-2 bg-white text-gray-500">Or</span>
              </div>
            </div>

            <Link href="/register">
            <button
            type="button"
            className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
              signup now
            </button>
            </Link>

            <div className="mt-6">
              <button
                type="button"
                className="w-full inline-flex justify-center items-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
              >
                <FaGoogle className="mr-2 text-red-500" />
                Sign up with Google
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    </div>

  );
}