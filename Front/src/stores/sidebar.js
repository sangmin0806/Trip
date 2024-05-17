import { ref } from 'vue';
import { defineStore } from 'pinia';
const sidebarActive = ref(false);
const input = ref('');
const triplist = ref([]); // triplist 배열 추가
const useSidebarStore = defineStore({
  id: 'sidebar',
  state: () => ({
    sidebarActive: sidebarActive.value,
    contentTypeId: null,
    input: input.value,
    triplist: triplist.value,
  }),
  actions: {
    toggleSidebar() {
      sidebarActive.value = !sidebarActive.value;
      this.sidebarActive = sidebarActive.value;
    },
    setSidebarActive(value) {
      sidebarActive.value = value;
      this.sidebarActive = value;
    },
    setContentTypeId(id) {
      this.contentTypeId = id;
    },
    setInput(input) {
      this.input = input;
    },
    clearInput() {
      this.input = '';
    },
    addTrip(item) {
      this.triplist.push(item);
    },
    removeTrip(index) {
      this.triplist.splice(index, 1);
    },
    clearTrips() {
      this.triplist = [];
    },
  },
});

export { useSidebarStore };
