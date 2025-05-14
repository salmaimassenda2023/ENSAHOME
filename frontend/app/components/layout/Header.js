import { IoIosNotifications } from "react-icons/io";
import Image from "next/image";
import {IoPersonCircleSharp} from "react-icons/io5";
import Link from "next/link";

export default function  Header(){

    return(
        <div className="bg-black text-white  flex items-center justify-between pl-5 h-16 ">

            {/*logo*/}
            <div className="pt-7 pb-0 ">
                <Image
                src="/logo_ensahome_white.png"
                alt="Logo de l'application"
                width={110}
                height={40}/>
            </div>

            {/*Logement et Equipement */}
            <div className="flex space-x-20 ">
                <Link href="/" className="hover:text-gray-300 transition">
                    <span>Logement</span>
                </Link>
                <Link href="/" className="hover:text-gray-300 transition">
                    <span>Equipement</span>
                </Link>
            </div>

            {/*not @ user*/}
            <div className="flex justify-end m-5 space-x-3 ">
                <Link href="/" className="hover:text-gray-300 transition">
                    <IoIosNotifications className="text-2xl" />
                </Link>
                <Link href="/" className="hover:text-gray-300 transition">
                    <IoPersonCircleSharp className="text-2xl" />
                </Link>



            </div>

        </div>
    )
}
