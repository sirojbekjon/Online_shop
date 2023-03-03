<template>
  <div>
    <input type="file" ref="fileInput" @change="onFileSelected" multiple>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      files: [],
      token: 'Bearer ' + sessionStorage.getItem('token'),
    };
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

      try {
        console.log(formData)
        const response = await axios.post("file/upload", formData,{headers:{Accept:'application/json','Content-Type': 'multipart/form-data','authorization': this.token}});

        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    }
  },
  created() {
    this.uploadFiles();
  }
};
</script>
