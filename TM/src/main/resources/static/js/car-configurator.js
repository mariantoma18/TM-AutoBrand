
    const colorSpinMap = {
        RED: "https://mariantoma.sirv.com/Elyssion_sedan/red_sedan/red_sedan.spin",
        BLUE: "https://mariantoma.sirv.com/Elyssion_sedan/blue_sedan/blue_sedan.spin",
        BLACK: "https://mariantoma.sirv.com/Elyssion_sedan/black_sedan/black_sedan.spin",
        SILVER: "https://mariantoma.sirv.com/Elyssion_sedan/silver_sedan/silver_sedan.spin",
        GREEN: "https://mariantoma.sirv.com/Elyssion_sedan/green_sedan/green_sedan.spin"
    };

    function changeColorFromData(element) {
        const colorKey = element.dataset.color;
        changeSpin(colorKey);
    }

    function changeSpin(colorKey) {
        const newUrl = colorSpinMap[colorKey];
        const oldSpinDiv = document.getElementById("carSpin");

        if (oldSpinDiv && newUrl) {
            const newSpinDiv = document.createElement("div");
            newSpinDiv.id = "carSpin";
            newSpinDiv.className = "Sirv";
            newSpinDiv.setAttribute("data-src", newUrl);

            oldSpinDiv.parentNode.replaceChild(newSpinDiv, oldSpinDiv);
            Sirv.start();
        } else {
            console.warn("The spin could not be updated. Check the colorKey or the element.");
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
};

    const engineImageMap = {
    PETROL_2_0_TSX: "https://mariantoma.sirv.com/Engine_type/2.0%20TSX.png",
    DIESEL_1_6_TDI: "https://mariantoma.sirv.com/Engine_type/2.0%20TDX.png"
};

    function changeEngineImage(engineKey) {
    const newUrl = engineImageMap[engineKey];
    const engineImg = document.getElementById("engineImage");

    if (engineImg && newUrl) {
        engineImg.setAttribute("src", newUrl);
    } else {
        console.warn("Could not update engine image. Check engineKey:", engineKey);
    }
};

    function enableSubmit() {
        const submitButton = document.getElementById("submitButton");
        if (submitButton) {
            submitButton.disabled = false;
        }
    }