const colorImageGalleryMap = {
    RED: [
        "https://mariantoma.sirv.com/Teraon_SUV/red_SUV/1.png",
        "https://mariantoma.sirv.com/Teraon_SUV/red_SUV/3.png",
        "https://mariantoma.sirv.com/Teraon_SUV/red_SUV/5.png"
    ],
    BLUE: [
        "https://mariantoma.sirv.com/Teraon_SUV/blue_SUV/1.png",
        "https://mariantoma.sirv.com/Teraon_SUV/blue_SUV/3.png",
        "https://mariantoma.sirv.com/Teraon_SUV/blue_SUV/5.png"
    ],
    BLACK: [
        "https://mariantoma.sirv.com/Teraon_SUV/black_SUV/1.png",
        "https://mariantoma.sirv.com/Teraon_SUV/black_SUV/3.png",
        "https://mariantoma.sirv.com/Teraon_SUV/black_SUV/5.png"
    ],
    SILVER: [
        "https://mariantoma.sirv.com/Teraon_SUV/silver_SUV/1.png",
        "https://mariantoma.sirv.com/Teraon_SUV/silver_SUV/3.png",
        "https://mariantoma.sirv.com/Teraon_SUV/silver_SUV/5.png"
    ],
    GREEN: [
        "https://mariantoma.sirv.com/Teraon_SUV/green_SUV/1.png",
        "https://mariantoma.sirv.com/Teraon_SUV/green_SUV/3.png",
        "https://mariantoma.sirv.com/Teraon_SUV/green_SUV/5.png"
    ]
};

let currentGalleryImages = [];
let currentIndex = 0;

function changeColorGallery(colorKey) {
    const imageUrls = colorImageGalleryMap[colorKey];
    if (imageUrls && imageUrls.length > 0) {
        currentGalleryImages = imageUrls;
        currentIndex = 0;
        updateCarouselImage();
    } else {
        console.warn("The gallery could not be updated. Check the colorKey:", colorKey);
    }
}

function updateCarouselImage() {
    const imageElement = document.getElementById("carouselImage");
    if (imageElement && currentGalleryImages.length > 0) {
        imageElement.src = currentGalleryImages[currentIndex];
    }
}

function prevImage() {
    if (currentGalleryImages.length > 0) {
        currentIndex = (currentIndex - 1 + currentGalleryImages.length) % currentGalleryImages.length;
        updateCarouselImage();
    }
}

function nextImage() {
    if (currentGalleryImages.length > 0) {
        currentIndex = (currentIndex + 1) % currentGalleryImages.length;
        updateCarouselImage();
    }
}

const interiorImageMap = {
    BLACK: "https://mariantoma.sirv.com/interior/black.webp",
    TAN: "https://mariantoma.sirv.com/interior/tan.webp",
    WHITE: "https://mariantoma.sirv.com/interior/white.jpg"
};

function changeInteriorImage(colorKey) {
    const newUrl = interiorImageMap[colorKey];
    const interiorImg = document.getElementById("interiorImage");

    if (interiorImg && newUrl) {
        interiorImg.setAttribute("src", newUrl);
        Sirv.start();
    } else {
        console.warn("Could not update interior image. Check colorKey:", colorKey);
    }
}

const engineImageMap = {
    PETROL_2_0_TSX: "https://mariantoma.sirv.com/Engine_type/2.0%20TSX.png",
    DIESEL_2_0_TDX: "https://mariantoma.sirv.com/Engine_type/2.0%20TDX.png"
};

function changeEngineImage(engineKey) {
    const newUrl = engineImageMap[engineKey];
    const engineImg = document.getElementById("engineImage");

    if (engineImg && newUrl) {
        engineImg.setAttribute("src", newUrl);
    } else {
        console.warn("Could not update engine image. Check engineKey:", engineKey);
    }
}

function enableSubmit() {
    const submitButton = document.getElementById("submitButton");
    if (submitButton) {
        submitButton.disabled = false;
    }
}
