import { ReactNode } from 'react';

export interface Commodite {
    nom: string;
    icon: ReactNode;
}

export interface Etat {
    non: string;
    icon: ReactNode;
}

export interface Logement {
    id: number;
    photos: string[];
    adresee: string;
    proximite: string;
    desc: string;
    type: string;
    nombrePieces: number;
    loyer: number;
    commodites: Commodite[];
}

export interface Equipement {
    id: number;
    photos: string[];
    etas: Etat;
    designation: string;
    desc: string;
    prix: number;
}

export interface Publication {
    id: number;
    typePub: string;
    photos: string[];
    etas?: Etat;
    designation?: string;
    desc: string;
    prix?: number;
    adresee?: string;
    proximite?: string;
    type?: string;
    nombrePieces?: number;
    loyer?: number;
    commodites?: Commodite[];
}

export interface NotificationLocataire {
    id: number;
    logement: Logement;
    message: string;
    detail: string;
    dateExpiration: string;
}

export const typeLogement = ["appartement", "Chambre", "studios"] as const; 