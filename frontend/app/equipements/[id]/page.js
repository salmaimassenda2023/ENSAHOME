// "use client"
// import {logements} from "@/data";
// import "../../../frontend/app/globals.css"
// import {useState} from "react";
//
//
// export default function LogementDetails({params}){
//     const [index, setIndex] = useState(0);
//     // get id of he product selected
//     const id = parseInt(params.id);
//      // get logement from data
//     const logement = logements.find(item=> item.id==id);
//
//
//     return(
//         <div>
//             <div className="product-detail-container">
//                 <div>
//                     <div className="image-container">
//                         {logement.photos && logement.photos[index] && (
//                             <img
//                                 src={logement.photos[index]}
//                                 alt="image not found"
//                                 className="product-detail-image"
//                             />
//                         )}
//                     </div>
//                     <div className="small-images-container">
//                         {logement.photos?.map((item, i) => (
//                             <img
//                                 key={i}
//                                 src={urlFor(item).url()}
//                                 className={i === index ? 'small-image selected-image' : 'small-image'}
//                                 onMouseEnter={() => setIndex(i)}
//                                 alt={`${name} view ${i + 1}`}
//                             />
//                         ))}
//                     </div>
//                 </div>
//             {/*    details*/}
//             </div>
//
//         </div>
//     )
// }
