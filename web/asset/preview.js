window.onload = function () {
        document.getElementById("photo").addEventListener(
                "change",
                function () {
                        const reader = new FileReader();
                        reader.onload = function () {
                                const dataURL = reader.result;
                                const output = document.getElementById("pre-photo");
                                output.src = dataURL;
                        };
                        reader.readAsDataURL(this.files[0]);
                },
                false
        );
};
