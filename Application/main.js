import * as THREE from "three";
import { GLTFLoader } from "three/addons/loaders/GLTFLoader.js";
import { OrbitControls } from "three/addons/controls/OrbitControls.js";

//declaring usefull variables
const renderer = new THREE.WebGLRenderer();
const modelLoader = new GLTFLoader();
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(
  75,
  window.innerWidth / window.innerHeight,
  0.1,
  1000
);
let geometry;
let material;

/*
//create the planeSize
const groundGeometry = new THREE.PlaneGeometry(5, 5);
groundGeometry.rotateX(-Math.PI / 2);
const groundMaterial = new THREE.MeshStandardMaterial({
  color: 0x555555,
  side: THREE.DoubleSide,
});
const groundMesh = new THREE.Mesh(groundGeometry, groundMaterial);
scene.add(groundMesh);
*/

//Adding the light
const light = new THREE.HemisphereLight(0xfffffff, 0x000000, 1);
scene.add(light);

//adding the model
modelLoader.load("./models/scene.gltf", (gltf) => {
  const model = gltf.scene;
  model.position.set(0, -6, 1);
  scene.add(model);
});

//Adding torax Hitbox
geometry = new THREE.BoxGeometry(2, 1.3, 0.9);
material = new THREE.MeshBasicMaterial({ color: 0x00ff00 });
material.wireframe = true;
const ToraxHitBox = new THREE.Mesh(geometry, material);
ToraxHitBox.position.set(0, 2.2, 0.5);
scene.add(ToraxHitBox);

//Adding abdoman Hitbox
geometry = new THREE.BoxGeometry(2, 1.5, 0.7);
material = new THREE.MeshBasicMaterial({ color: 0xdb1801 });
material.wireframe = true;
const abdomanHitBox = new THREE.Mesh(geometry, material);
abdomanHitBox.position.set(0, 0.8, 0.6);
scene.add(abdomanHitBox);

//Adding back Hitbox
geometry = new THREE.BoxGeometry(2, 2.5, 0.9);
material = new THREE.MeshBasicMaterial({ color: 0xdb0d42 });
material.wireframe = true;
const backHitBox = new THREE.Mesh(geometry, material);
backHitBox.position.set(0, 1.5, -0.6);
scene.add(backHitBox);

//Adding quadriceps Hitbox
geometry = new THREE.BoxGeometry(2.5, 2.5, 0.7);
material = new THREE.MeshBasicMaterial({ color: 0x0d1edb });
material.wireframe = true;
const legsHitBox = new THREE.Mesh(geometry, material);
legsHitBox.position.set(0, -1.2, 0.4);
scene.add(legsHitBox);

//Adding gluteus Hitbox
geometry = new THREE.BoxGeometry(2, 1.3, 0.7);
material = new THREE.MeshBasicMaterial({ color: 0xdbca0d });
material.wireframe = true;
const gluteusHitBox = new THREE.Mesh(geometry, material);
gluteusHitBox.position.set(0, -0.4, -0.6);
scene.add(gluteusHitBox);

//Adding hamstring Hitbox
geometry = new THREE.BoxGeometry(2.5, 1.7, 0.7);
material = new THREE.MeshBasicMaterial({ color: 0x8d0ddb });
material.wireframe = true;
const hamstringHitBox = new THREE.Mesh(geometry, material);
hamstringHitBox.position.set(0, -2, -0.6);
scene.add(hamstringHitBox);

//Adding calf Hitbox
geometry = new THREE.BoxGeometry(2.5, 2.3, 0.7);
material = new THREE.MeshBasicMaterial({ color: 0xdc85b1 });
material.wireframe = true;
const calfHitBox = new THREE.Mesh(geometry, material);
calfHitBox.position.set(0, -4, -0.9);
scene.add(calfHitBox);

//Adding Left Shoulders Hitbox
geometry = new THREE.BoxGeometry(0.7, 0.7, 1);
material = new THREE.MeshBasicMaterial({ color: 0x0dc7db });
material.wireframe = true;
const leftShouldersHitBox = new THREE.Mesh(geometry, material);
leftShouldersHitBox.position.set(-1.4, 2.4, -0.2);
scene.add(leftShouldersHitBox);

//Adding Right Shoulders Hitbox
geometry = new THREE.BoxGeometry(0.7, 0.7, 1);
material = new THREE.MeshBasicMaterial({ color: 0x0dc7db });
material.wireframe = true;
const rightShouldersHitBox = new THREE.Mesh(geometry, material);
rightShouldersHitBox.position.set(1.4, 2.4, -0.2);
scene.add(rightShouldersHitBox);

//Adding Left Biceps Hitbox
geometry = new THREE.BoxGeometry(1, 1.3, 1.1);
material = new THREE.MeshBasicMaterial({ color: 0x0ddb65 });
material.wireframe = true;
const leftBicepsHitBox = new THREE.Mesh(geometry, material);
leftBicepsHitBox.position.set(-1.4, 1.4, -0.25);
scene.add(leftBicepsHitBox);

//Adding Right Biceps Hitbox
geometry = new THREE.BoxGeometry(1, 1.3, 1.1);
material = new THREE.MeshBasicMaterial({ color: 0x0ddb65 });
material.wireframe = true;
const rightBicepsHitBox = new THREE.Mesh(geometry, material);
rightBicepsHitBox.position.set(1.4, 1.4, -0.25);
scene.add(rightBicepsHitBox);

//Adding Left forearm Hitbox
geometry = new THREE.BoxGeometry(0.8, 1, 1);
material = new THREE.MeshBasicMaterial({ color: 0xdb8d85 });
material.wireframe = true;
const leftForearmHitBox = new THREE.Mesh(geometry, material);
leftForearmHitBox.position.set(1.8, 0.25, -0.25);
scene.add(leftForearmHitBox);

//Adding Right forearm Hitbox
geometry = new THREE.BoxGeometry(0.8, 1, 1);
material = new THREE.MeshBasicMaterial({ color: 0xdb8d85 });
material.wireframe = true;
const rightForearmHitBox = new THREE.Mesh(geometry, material);
rightForearmHitBox.position.set(-1.8, 0.25, -0.25);
scene.add(rightForearmHitBox);

//Adding color for the background
const color = new THREE.Color().setHex(0x112233);
scene.background = color;

//adding the scene on the web site
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.setAnimationLoop(animate);
document.body.appendChild(renderer.domElement);

//set position of camera
camera.position.set(0, 0, 2);

//config the orbit control
const OrbitControl = new OrbitControls(camera, renderer.domElement);
/*
OrbitControl.minDistance = 7.5;
OrbitControl.maxDistance = 9;
OrbitControl.minPolarAngle = Math.PI / 4;
OrbitControl.maxPolarAngle = Math.PI / 2.5;

//True = you can move the camera
//false = you can't move the camera
OrbitControl.enablePan = true;
*/

//redering on the web site
function animate() {
  OrbitControl.update();
  renderer.render(scene, camera);
}

/* tips:
  USING THE LEFT BUTTOM YOU CAN ROTATE THE CAMERA
  USING THE RIGHT BUTTOM YOU CAN MOVE THE CAMERA
*/
