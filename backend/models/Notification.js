const mongoose = require('mongoose');

const notificationSchema = new mongoose.Schema({
    logement: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Logement',
        required: false
    },
    equipement: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Equipement',
        required: false
    },
    message: {
        type: String,
        required: true
    },
    detail: {
        type: String,
        required: true
    },
    dateCreation: {
        type: Date,
        default: Date.now
    },
    dateExpiration: {
        type: Date,
        required: true
    },
    isRepubliable: {
        type: Boolean,
        default: false
    },
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    isActive: {
        type: Boolean,
        default: true
    }
});

// Méthode pour vérifier si la notification est expirée
notificationSchema.methods.isExpired = function() {
    return new Date() > this.dateExpiration;
};

// Index TTL pour supprimer automatiquement les notifications expirées
notificationSchema.index({ dateExpiration: 1 }, { expireAfterSeconds: 0 });

module.exports = mongoose.model('Notification', notificationSchema); 