import { defineStore } from 'pinia';

const useSidebarStore = defineStore({
    id: 'sidebar',
    state: () => ({
        sidebarActive: false,
        contentTypeId: null,
        input: '',
        tripList: [],
        tripListActive: false,
    }),
    actions: {
        toggleSidebar() {
            this.sidebarActive = !this.sidebarActive;
        },
        setSidebarActive(value) {
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
            this.tripList.push(item);
        },
        removeTrip(contentId) {
            this.tripList = this.tripList.filter((trip) => trip.contentId !== contentId);
        },
        clearTrips() {
            this.tripList = [];
        },
    },
});

export { useSidebarStore };
