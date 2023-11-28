<script>
import { QuillEditor } from "@vueup/vue-quill";
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import debounce from "lodash/debounce";

import "@vueup/vue-quill/dist/vue-quill.snow.css";
import QnaAPI from "@/api/QnaAPI.js";

export default {
  components: {
    QuillEditor,
  },
  data() {
    return {
      content: reactive(""),
      readOnlyFlag: ref(false),
    };
  },
  props: ["EditableFlag", "prevContent"],
  computed: {
    editor() {
      return null;
    },
  },
  mounted() {
    console.log("this is Quill instance:", this.editor);
    console.log("EditableFlag : " + this.EditableFlag);
  },
  watch: {
    content: debounce(function (val) {
      //   console.log("content changed", val);
      this.$emit("updateContent", val);
    }, 100),
    EditableFlag: function (val) {
      console.log("EditableFlag changed", val);
      this.readOnlyFlag = !val;
    },
    prevContent: function (val) {
      console.log("prevContent changed", val);
      this.content = val;
    },
  },
};
</script>

<template>
  <div class="editorTextBox">
    <QuillEditor
      :theme="snow"
      :toolbar="essential"
      :readOnly="readOnlyFlag"
      v-model:content="content"
      contentType="html"
      @update:content="onEditorContentChange"
    />
  </div>
</template>

<style scoped>
.editorTextBox {
  margin-bottom: 50px;
  height: 400px;
}
</style>
