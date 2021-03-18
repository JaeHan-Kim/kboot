let form = new Vue({
    el: '#form',
    data: {
        form: {
            name: 'a',
            email: '',
            images: '',
        }
    },
    methods: {
        save: function () {
            console.log(this.form.images.length);
            console.log(this.form.images);
            axios({
                method: 'post',
                url: 'http://localhost:8080/api/save',
                data: this.form,
            }).then(function (response) {
                console.log(response);
            });

        },
        fileSubmit: function (event) {
            let fileList = event.target.files;
            if (fileList) {
                const files = Array.from(event.target.files);
                Promise.all(files.map(file => {
                    return (new Promise((resolve, reject) => {
                        let reader = new FileReader();
                        reader.addEventListener('load', ev => {
                            console.log(ev.target.result);
                            console.log(ev.name);
                            resolve(ev.target.result);
                        });
                        reader.addEventListener('error', reject);
                        reader.readAsDataURL(file);
                    }));
                }))
                    .then(images => {
                        console.log(images);
                        console.log(`images $images[0]`);
                        this.form.images = images;
                    }, error => {
                        console.error(error);
                    });
            }
        }
    }
});