import * as THREE from "three";
import { GLTFLoader } from "three/addons/loaders/GLTFLoader.js";
import { OrbitControls } from "three/addons/controls/OrbitControls.js";

//Creating model
  //declaring usefull variables
  const renderer = new THREE.WebGLRenderer();
  const modelLoader = new GLTFLoader();
  const scene = new THREE.Scene();
  const WIDTH = document.getElementById("model").offsetWidth;
  const HEIGHT = document.getElementById("model").offsetHeight;
  const camera = new THREE.PerspectiveCamera(75, WIDTH / HEIGHT, 0.1, 1000);
  const raycaster = new THREE.Raycaster();
  const pointer = new THREE.Vector2();
  const MINDISTANCE = 11;
  const MAXDISTANCE = 12;

  //Adding the light
  const light = new THREE.HemisphereLight(0xfffffff, 0x000000, 1);
  scene.add(light);

  //adding the model
  modelLoader.load("./models/scene.gltf", (gltf) => {
    const model = gltf.scene;
    model.position.set(0, -6, 1);
    scene.add(model);
  });

  function CreateHitBox(
    width,
    height,
    depth,
    hitboxColor,
    positionX,
    positionY,
    positionZ
  ) {
    const geometry = new THREE.BoxGeometry(width, height, depth);
    const material = new THREE.MeshBasicMaterial({
      color: hitboxColor,
    });
    material.visible = false;
    material.wireframe = false;

    const hitbox = new THREE.Mesh(geometry, material);
    hitbox.position.set(positionX, positionY, positionZ);
    scene.add(hitbox);
  }

  //Adding torax Hitbox
  CreateHitBox(2, 1.3, 0.9, 0x00ff00, 0, 2.2, 0.5);

  //Adding abdoman Hitbox
  CreateHitBox(2, 1.5, 0.7, 0xdb1801, 0, 0.8, 0.6);

  //Adding back Hitbox
  CreateHitBox(2, 2.5, 0.9, 0xdb0d42, 0, 1.5, -0.6);

  //Adding quadriceps Hitbox
  CreateHitBox(2.5, 2.5, 0.7, 0x0d1edb, 0, -1.2, 0.4);

  //Adding gluteus Hitbox
  CreateHitBox(2, 1.3, 0.7, 0xdbca0d, 0, -0.4, -0.6);

  //Adding hamstring Hitbox
  CreateHitBox(2.5, 1.7, 0.7, 0x8d0ddb, 0, -2, -0.6);

  //Adding calf Hitbox
  CreateHitBox(2.5, 2.3, 0.7, 0xdc85b1, 0, -4, -0.9);

  //Adding Left Shoulders Hitbox
  CreateHitBox(0.7, 0.7, 1, 0x0dc7db, -1.4, 2.4, -0.2);

  //Adding Right Shoulders Hitbox
  CreateHitBox(0.7, 0.7, 1, 0x0dc7db, 1.4, 2.4, -0.2);

  //Adding Left Biceps Hitbox
  CreateHitBox(1, 1.3, 1.1, 0x0ddb65, -1.4, 1.4, -0.25);

  //Adding Right Biceps Hitbox
  CreateHitBox(1, 1.3, 1.1, 0x0ddb65, 1.4, 1.4, -0.25);

  //Adding Left forearm Hitbox
  CreateHitBox(0.8, 1, 1, 0xdb8d85, 1.8, 0.25, -0.25);

  //Adding Right forearm Hitbox
  CreateHitBox(0.8, 1, 1, 0xdb8d85, -1.8, 0.25, -0.25);

  //Adding color for the background
  const color = new THREE.Color().setHex(0xffffff);
  scene.background = color;

  //set position of camera
  camera.position.set(0, -10, 5);

  //config the orbit control
  let OrbitControl = new OrbitControls(camera, renderer.domElement);
  OrbitControl.minDistance = MINDISTANCE;
  OrbitControl.maxDistance = MAXDISTANCE;
  OrbitControl.minPolarAngle = Math.PI / 4;
  OrbitControl.maxPolarAngle = Math.PI / 2.5;

  //True = you can move the camera
  //false = you can't move the camera
  OrbitControl.enablePan = false;

  function onPointerMove(event) {
    const model = document.getElementById("model");
    const rect = model.getBoundingClientRect();

    pointer.x = ((event.clientX - rect.left) / rect.width) * 2 - 1;
    pointer.y = -((event.clientY - rect.top) / rect.height) * 2 + 1;

    window.requestAnimationFrame(render);
  }

  function render() {
    // update the picking ray with the camera and pointer position
    raycaster.setFromCamera(pointer, camera);

    // calculate objects intersecting the picking ray
    const intersects = raycaster.intersectObjects(scene.children);
    const position = intersects[0].object.position;

    OrbitControl.target = position;
    OrbitControl.minDistance = 2;
    OrbitControl.maxDistance = 2.5;
  }

  //redering on the web site
  function animate() {
    OrbitControl.update();
    renderer.render(scene, camera);
  }

  window.addEventListener("keydown", (event) => {
    if (event.key === " ") {
      OrbitControl.target = new THREE.Vector3(0, 0, 0);
      OrbitControl.minDistance = MINDISTANCE;
      OrbitControl.maxDistance = MAXDISTANCE;
    }
  });

  window.addEventListener("mousedown", onPointerMove);

  //adding the scene on the web site
  renderer.setSize(WIDTH, HEIGHT);
  renderer.setAnimationLoop(animate);
  document.getElementById("model").appendChild(renderer.domElement);

  /* tips:
  USING THE LEFT BUTTOM YOU CAN ROTATE THE CAMERA
  USING THE RIGHT BUTTOM YOU CAN MOVE THE CAMERA
*/
