//Frontend/script.js


const API_URL = "http://localhost:8080";
let carritoActual = null;
document.addEventListener("DOMContentLoaded", () => {
    cargarProductos();
});

// Traer todos los productos
function cargarProductos() {
    fetch(`${API_URL}/productos`)
    .then(res => res.json())
    .then(productos => {
        const contenedor = document.getElementById("contenedor-productos");
        contenedor.innerHTML = ""; // Limpiar antes de dibujar

        productos.forEach(prod => {
            // Si el stock es 0, deshabilita el botón
            const sinStock = prod.stock === 0;
            const botonHtml = sinStock
            ? `<button class="btn btn-secondary mt-auto" disabled>Sin Stock</button>`
            : `<button class="btn btn-primary mt-auto" onclick="agregarAlCarrito(${prod.id})">+ Agregar al Carrito</button>`;

            //Tarjeta del producto usando los datos de tu MySQL
            const card = `
            <div class="col-md-4 mb-4">
            <div class="card h-100 shadow-sm">
            <img src="${prod.imagenUrl}" class="card-img-top" alt="${prod.nombre}" style="height: 250px; object-fit: contain; padding: 15px;">
            <div class="card-body d-flex flex-column">
            <h5 class="card-title">${prod.nombre}</h5>
            <p class="card-text text-muted mb-1">${prod.categoria.nombre}</p>
            <h4 class="text-success">$${prod.precio}</h4>
            <p class="card-text"><strong>Stock:</strong> ${prod.stock} unidades</p>
            ${botonHtml}
            </div>
            </div>
            </div>
            `;
            contenedor.innerHTML += card;
        });
    })
    .catch(error => console.error("Error cargando productos:", error));
}

// Crear un carrito vacío
function crearCarrito() {
    fetch(`${API_URL}/carritos`, { method: "POST" })
    .then(res => res.json())
    .then(carrito => {
        carritoActual = carrito.id;
        // Actualiza la interfaz
        document.getElementById("carrito-status").textContent = `ID ${carritoActual} (Activo)`;
        document.getElementById("carrito-status").className = "text-success fw-bold";
        alert(`¡Carrito creado exitosamente con el ID: ${carritoActual}!`);
    })
    .catch(error => console.error("Error creando carrito:", error));
}

// Agregar producto al carrito y descontar stock
function agregarAlCarrito(productoId) {
    if (!carritoActual) {
        alert("¡Por favor, creá un carrito primero haciendo clic en el botón verde!");
        return;
    }

    fetch(`${API_URL}/carritos/${carritoActual}/productos/${productoId}`, { method: "POST" })
    .then(res => {
        if (res.ok) {
            alert("¡Producto agregado al carrito!");
            cargarProductos();
        } else {
            return res.text().then(text => { throw new Error(text) });
        }
    })
    .catch(error => alert(`Error de la API: ${error.message}`));
}
