import {FaCouch, FaParking, FaSnowflake, FaWifi} from "react-icons/fa";



export const logements=[
    //Appartement 1

    {
        id:1,
        photos:["/aprt_nn.png" ,"/image-.png","/aprt_nn.png","/image-.png"],
        adresee:"Khouribga Nahda",
        proximite:"15 ",
        desc:"EXCLUSIVITÉ - EN BON ÉTAT GÉNÉRAL En location : venez découvrir à AVESNES SUR HELPE (59440) cet appartement de 2 pièces de 40 m². Il est agencé comme suit :un séjour de 17 m², une chambre, une cuisine équipée et une salle d'eau.",
        type:"Appartement",
        nombrePieces:2 ,
        loyer : 200,
        commodites :[
            {nom: "Wifi" ,icon:<FaWifi />},
            {nom: "Parking" ,icon:<FaParking />},
        ]
    },
    //Appartement 2
    {
        id:2,
        photos:["/aprt1.png" ,"/image-2.png","/aprt1.png","/image-2.png"],
        adresee:"Khouribga - Nahda",
        proximite:"15 ",
        desc:"cet appartement de 2 pièces de 40 m². Il est agencé comme suit :un séjour de 17 m², une chambre, une cuisine équipée et une salle d'eau.",
        type:"Appartement",
        nombrePieces:2 ,
        loyer : 200,
        commodites :[
            {nom: "Meuble" ,icon:<FaCouch />},
            {nom: "climat" ,icon:<FaSnowflake />},
            {nom: "Wifi" ,icon:<FaWifi />},
            {nom: "Parking" ,icon:<FaParking />},
        ]
    },
    //Appartement 3
    {
        id:3,
        photos:["/aprt_nn.png" ,"/aprt_nn.png","/aprt_nn.png"],
        adresee:"Khouribga Nahda",
        proximite:"15 ",
        desc:"desc",
        type:"Appartement",
        nombrePieces:2 ,
        loyer : 200,
        commodites :[
            {nom: "Wifi" ,icon:<FaWifi />},
            {nom: "Parking" ,icon:<FaParking />},
        ]
    },
    //Appartement 4
    {
        id:4,
        photos:["/aprt1.png" ,"/image-2.png","/aprt1.png","/image-2.png"],
        adresee:"Khouribga Nahda",
        proximite:"15 ",
        desc:"desc",
        type:"Appartement",
        nombrePieces:2 ,
        loyer : 200,
        commodites :[
            {nom: "Meuble" ,icon:<FaCouch />},
            {nom: "climat" ,icon:<FaSnowflake />},
        ]
    }
];

export const equipements=[
    {
        id:1,
        photos:"/ordi.png",
        designation:"PC Portable",
        desc:"petite description",
        prix:3000,
    },
    {
        id:2,
        photos:"/salle.png",
        designation:"Salle a manger",
        desc:"petite description",
        prix: 0,
    },
    {
        id:3,
        photos:"/ordi.png",
        designation:"PC Portable",
        desc:"petite description",
        prix:3000,
    },
    {
        id:4,
        photos:"/salle.png",
        designation:"Salle a manger",
        desc:"petite description",
        prix: 0,
    }
]















