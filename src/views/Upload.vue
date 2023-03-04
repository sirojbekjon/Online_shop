<template>
  <div>
    <input type="file" ref="fileInput" @change="onFileSelected" multiple>
    <div>
      <img :src="imageUrl"/>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {

    return {
      files: [],
      token: 'Bearer ' + sessionStorage.getItem('token'),
      imageFileData: null,
      imageUrl: ''
    };
  },

  mounted() {
    axios.get('file/download/46', {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'multipart/form-data',
        'authorization': this.token
      },
      responseType: 'blob' // set response type to blob
    })
        .then(response => {
          const blobUrl = URL.createObjectURL(response.data); // create blob URL
          this.imageUrl = blobUrl;
        })
        .catch(error => {
          console.log(error);
        });
  },



  methods: {
    onFileSelected() {
      this.files = this.$refs.fileInput.files;
      this.uploadFiles()
    },
    async uploadFiles() {
      const formData = new FormData();

      for (let i = 0; i < this.files.length; i++) {
        formData.append('files', this.files[i], this.files[i].name);
      }
        await axios.post("file/upload", formData,
            {headers:{Accept:'application/json','Content-Type': 'multipart/form-data','authorization': this.token}});
    }
  },
  created() {
    this.uploadFiles();
  }
};
</script>
