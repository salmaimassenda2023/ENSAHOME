"use client"; // obligatoire car usePathname est un hook client

import { IoIosNotifications } from "react-icons/io";
import Image from "next/image";
import { IoPersonCircleSharp } from "react-icons/io5";
import Link from "next/link";
import { usePathname } from "next/navigation";

export default function Header() {
    const pathname = usePathname();

    return (
        <div className="bg-black text-white flex items-center justify-between pl-5 h-17">
            {/* Logo */}
            HeaderRegister
        </div>
    );
}
