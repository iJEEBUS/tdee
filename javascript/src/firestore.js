import firebase from "@firebase/app";
import "@firebase/firestore";

const config = {
    apiKey: "AIzaSyD7p6K2MwRoqTX9b4r4-PL2XzH41lmbcWo",
    authDomain: "tdee-f5ef5.firebaseapp.com",
    databaseURL: "https://tdee-f5ef5.firebaseio.com",
    projectId: "tdee-f5ef5",
    storageBucket: "tdee-f5ef5.appspot.com",
    messagingSenderId: "99600157868",
    appId: "1:99600157868:web:ee5246e7d52894a7"
};

const app = firebase.initializeApp(config);
const firestore = firebase.firestore(app);

export default firestore;