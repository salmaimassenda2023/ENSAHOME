import {FaCouch, FaParking, FaSnowflake, FaWifi} from "react-icons/fa";



export const logements=[
    //Appartement 1

    {
        id:1,
        photos:"/aprt_nn.png" ,
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
        photos:"/aprt1.png" ,
        type:"Appartement",
        nombrePieces:2 ,
        loyer : 200,
        commodites :[
            {nom: "Meuble" ,icon:<FaCouch />},
            {nom: "climat" ,icon:<FaSnowflake />},
        ]
    },
    //Appartement 3
    {
        id:3,
        photos:"/aprt_nn.png" ,
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
        photos:"/aprt1.png" ,
        type:"Appartement",
        nombrePieces:2 ,
        loyer : 200,
        commodites :[
            {nom: "Meuble" ,icon:<FaCouch />},
            {nom: "climat" ,icon:<FaSnowflake />},
        ]
    }
];