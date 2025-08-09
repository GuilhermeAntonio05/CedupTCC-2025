import * as THREE from "three";
import { GLTFLoader } from "three/addons/loaders/GLTFLoader.js";
import { OrbitControls } from "three/addons/controls/OrbitControls.js";

const renderer = new THREE.WebGLRenderer();
const modelLoader = new GLTFLoader();
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(
  75,
  window.innerWidth / window.innerHeight,
  0.1,
  1000
);

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
const light = new THREE.HemisphereLight(0xfffffff, 0x00000, 1);
scene.add(light);

//adding the model
modelLoader.load("./models/scene.gltf", (gltf) => {
  const model = gltf.scene;
  model.position.set(0, -6, 1);
  scene.add(model);
});

//Adding color for the background
const color = new THREE.Color().setHex(0x112233);
scene.background = color;

//adding the scene on the web site
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.setAnimationLoop(animate);
document.body.appendChild(renderer.domElement);

//set position of camera
camera.position.set(0, 0, 0);

//config the orbit control
const OrbitControl = new OrbitControls(camera, renderer.domElement);
OrbitControl.minDistance = 7.5;
OrbitControl.minPolarAngle = Math.PI / 4;
OrbitControl.maxPolarAngle = Math.PI / 2.5;
OrbitControl.enablePan = false;

//redering on the web site
function animate() {
  //OrbitControl.update();
  renderer.render(scene, camera);
}

/* tips* 
  USING THE LEFT BUTTOM YOU CAN ROTATE THE CAMERA
  USING THE RIGHT BUTTOM YOU CAN MOVE THE CAMERA
*/
