<template>
    <div id="create-project-page">
        <SubHeaderWithTitle title="Criar novo projeto"/>
        
        <ProjectForm objective='create'/>

        <Dialog/>
    </div>
</template>

<script>
import SubHeaderWithTitle from '@/components/SubHeaderWithTitle.vue'
import Dialog from '@/components/Dialog.vue'
import ProjectForm from '@/components/ProjectForm.vue'

export default {
    name: 'CreateProject',
    components: {
        SubHeaderWithTitle,
        Dialog,
        ProjectForm,
    },
}
</script>

<style>
</style>