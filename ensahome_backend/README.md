# 📢 API Annonces – Documentation

## 🔗 Endpoints disponibles

---

### 📄 Annonces

- **Récupérer toutes les annonces**
  - **GET** `http://localhost:8081/announcements`

- **Récupérer les annonces d'un utilisateur**
  - **GET** `http://localhost:8081/announcements/{userId}`
  - **Exemple** :  
    `http://localhost:8081/announcements/684649c1d3d5bc5c062ab48f`

---

## 🏠 Logement

### ➕ Créer une annonce de logement  
(Crée un **logement** et une **annonce** associée)

- **POST** `http://localhost:8081/announcement/logement`
- **Body (JSON)** :

```json
{
  "commodites": ["MEUBLE"],
  "description": "Test",
  "loyer": "2000",
  "nomProprietaire": "Mouad",
  "nombrePieces": "3",
  "photos": ["2400.jpg_wh300.jpg"],
  "quartier": "22",
  "telephone": "0645568753",
  "type": "APPARTEMENT",
  "ville": "Khouribga"
}
```

### ✏️ Modifier un logement  
> 🚧 *À faire*

### 🗑️ Supprimer un logement  
> 🚧 *À faire*

---

## 🔧 Équipement

### ➕ Créer une annonce d’équipement  
(Crée un **équipement** et une **annonce** associée)

- **POST** `http://localhost:8081/announcement/equipement`
- **Body (JSON)** :

```json
{
  "description": "Test",
  "etat": "NEUF",
  "photos": ["2400.jpg_wh300.jpg"],
  "prix": "200"
}
```

### ✏️ Modifier un équipement  
> 🚧 *À faire*

### 🗑️ Supprimer un équipement  
> 🚧 *À faire*

---

## ✅ À faire (TODO)

- [ ] Implémenter la modification et suppression de **logement**
- [ ] Implémenter la modification et suppression d’**équipement**
- Stockage des photos liées aux annonces !

---